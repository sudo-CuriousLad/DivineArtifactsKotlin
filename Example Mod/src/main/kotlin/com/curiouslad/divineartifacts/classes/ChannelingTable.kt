package com.curiouslad.divineartifacts.classes

import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Material
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class ChannelingTable : Block(FabricBlockSettings.of(Material.METAL).strength(4.0f)) {
    val CHARGED = BooleanProperty.of("charged")
    init {
        val defaultState = getStateManager().defaultState.with(CHARGED, false)
    }
    override fun appendProperties(stateManager: StateManager.Builder<Block?, BlockState?>) {
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

}