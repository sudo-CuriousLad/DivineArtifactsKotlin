package com.curiouslad.divineartifacts.classes

import com.curiouslad.divineartifacts.interfaces.ImplementedInventory
import com.curiouslad.divineartifacts.items.ModItems
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockEntityProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World


class ChannelingTable : Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque()),BlockEntityProvider {

    init {
        val defaultState = defaultState.with(CHARGED, true)
    }
    override fun appendProperties(stateManager: StateManager.Builder<Block?, BlockState?>) {
        super.appendProperties(stateManager)
        stateManager.add(CHARGED)
    }



    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos?,
        player: PlayerEntity,
        hand: Hand?,
        hit: BlockHitResult?
    ): ActionResult? {
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1f, 1f)
        world.setBlockState(pos, state.with(CHARGED, true))
        return ActionResult.SUCCESS
    }
    override fun onSteppedOn(world: World, pos: BlockPos?, state: BlockState, entity: Entity?) {
        if (world.getBlockState(pos).get(CHARGED)) {
            //Summoning the Lighting Bolt at the block
            val lightningEntity = EntityType.LIGHTNING_BOLT.create(world)
            lightningEntity!!.refreshPositionAfterTeleport(Vec3d.ofBottomCenter(pos))
            world.spawnEntity(lightningEntity)
        }
        world.setBlockState(pos, state.with(CHARGED, false))
        super.onSteppedOn(world, pos, state, entity)
    }

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity? {
        return ChannelingTableEntity(pos, state)
    }


    companion object{
        val CHARGED: BooleanProperty = BooleanProperty.of("charged")
    }

}

class ChannelingTableEntity(pos: BlockPos?, state: BlockState?) :
    BlockEntity(ModItems.CHANNELING_TABLE_ENTITY, pos, state), ImplementedInventory {
    private var number = 7


    // Serialize the BlockEntity
    override fun writeNbt(tag: NbtCompound): NbtCompound? {
        super.writeNbt(tag)

        // Save the current value of the number to the tag
        tag.putInt("number", number)
        return tag
    }

    override val items: DefaultedList<ItemStack?>
        get() = TODO("Not yet implemented")

    override fun markDirty() {
        TODO("Not yet implemented")
    }

    override fun readNbt(tag: NbtCompound) {
        super.readNbt(tag)
        number = tag.getInt("number")
    }


    }

