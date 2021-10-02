package com.curiouslad.divineartifacts.items

import com.curiouslad.divineartifacts.DivineArtifacts
import com.curiouslad.divineartifacts.classes.ChannelingTable
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
//import com.curiouslad.divineartifacts.classes.ChannelingTable
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Block
import net.minecraft.block.Material
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup


object ModItems {
    val DIVINE_CORE = Item(FabricItemSettings().group(ItemGroup.MISC))
    val MURASAMA = Item(FabricItemSettings().group(ItemGroup.COMBAT))
    val CHANNELING_TABLE = Block(FabricBlockSettings.of(Material.METAL).strength(4.0f))



    fun registerItems() {


    }

}
