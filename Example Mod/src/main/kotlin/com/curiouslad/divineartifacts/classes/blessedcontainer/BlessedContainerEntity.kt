package com.curiouslad.divineartifacts.classes.blessedcontainer

import com.curiouslad.divineartifacts.items.ModItems
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.collection.DefaultedList
import net.minecraft.util.math.BlockPos

class BlessedContainerEntity(pos: BlockPos?, state: BlockState?) :
    BlockEntity(ModItems.BLESSED_CONTAINER_ENTITY, pos, state) { //ImplementedInventory,


    private var number = 7
    // Serialize the BlockEntity
    override fun writeNbt(tag: NbtCompound): NbtCompound {
        super.writeNbt(tag)

        // Save the current value of the number to the tag
        tag.putInt("number", number)
        return tag
    }

    val items: DefaultedList<ItemStack?> = DefaultedList.ofSize(2, ItemStack.EMPTY)

    override fun markDirty() {
        TODO("Not yet implemented")
    }

    override fun readNbt(tag: NbtCompound) {
        super.readNbt(tag)
        number = tag.getInt("number")
    }




}