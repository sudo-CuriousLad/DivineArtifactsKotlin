package com.curiouslad.divineartifacts.classes.blessedcontainer

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









