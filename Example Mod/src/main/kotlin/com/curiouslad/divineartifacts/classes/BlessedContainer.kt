package com.curiouslad.divineartifacts.classes

import com.curiouslad.divineartifacts.interfaces.ImplementedInventory
import com.curiouslad.divineartifacts.items.ModItems
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.inventory.Inventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import software.bernie.example.item.JackInTheBoxItem
import software.bernie.geckolib3.GeckoLib
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.model.AnimatedGeoModel


class BlessedContainer : Block(FabricBlockSettings.of(Material.METAL).strength(4.0f).nonOpaque()), BlockEntityProvider {

    override fun createBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return BlessedContainerEntity(pos, state)
    }

    override fun getRenderType(state: BlockState?): BlockRenderType {
        return BlockRenderType.ENTITYBLOCK_ANIMATED
    }


    override fun onUse(
        blockState: BlockState?,
        world: World,
        blockPos: BlockPos?,
        player: PlayerEntity,
        hand: Hand?,
        blockHitResult: BlockHitResult?
    ): ActionResult {
        if (world.isClient) return ActionResult.SUCCESS
        val blockEntity = world.getBlockEntity(blockPos) as Inventory?
        if (!player.getStackInHand(hand).isEmpty) {

            if (blockEntity!!.getStack(0).isEmpty) {
                // Put the stack the player is holding into the inventory
                blockEntity.setStack(0, player.getStackInHand(hand).copy())
                // Remove the stack from the player's hand
                player.getStackInHand(hand).count = 0
            } else if (blockEntity.getStack(1).isEmpty) {
                blockEntity.setStack(1, player.getStackInHand(hand).copy())
                player.getStackInHand(hand).count = 0
            } else {
                // If the inventory is full we'll print its contents
                println(
                    "The first slot holds "
                            + blockEntity.getStack(0) + " and the second slot holds " + blockEntity.getStack(1)
                )
            }
        }
        return ActionResult.SUCCESS
    }


}

class BlessedContainerEntity(pos: BlockPos?, state: BlockState?) :
    BlockEntity(ModItems.BLESSED_CONTAINER_ENTITY, pos, state), ImplementedInventory, IAnimatable {
    private var number = 7
    private val factory = AnimationFactory(this)
    // Serialize the BlockEntity
    override fun writeNbt(tag: NbtCompound): NbtCompound {
        super.writeNbt(tag)

        // Save the current value of the number to the tag
        tag.putInt("number", number)
        return tag
    }

    override val items: DefaultedList<ItemStack?> = DefaultedList.ofSize(2, ItemStack.EMPTY)

    override fun markDirty() {
        TODO("Not yet implemented")
    }

    override fun readNbt(tag: NbtCompound) {
        super.readNbt(tag)
        number = tag.getInt("number")
    }

    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState? {
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.blessed_container.open", false))
        return PlayState.CONTINUE
    }

    override fun registerControllers(p0: AnimationData) {
        p0.addAnimationController(AnimationController(this, "controller", 0f, this::predicate))
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }


}

class BlessedContainerModel : AnimatedGeoModel<BlessedContainerEntity>() {
    override fun getModelLocation(`object`: BlessedContainerEntity?): Identifier {
        return Identifier(GeckoLib.ModID, "geo/blessed_container.geo.json")
    }

    override fun getTextureLocation(`object`: BlessedContainerEntity?): Identifier {
        return Identifier(GeckoLib.ModID, "textures/block/blessed_container.png")
    }

    override fun getAnimationFileLocation(animatable: BlessedContainerEntity?): Identifier {
        return Identifier(GeckoLib.ModID, "animations/blessed_container.animation.json")
    }

}





