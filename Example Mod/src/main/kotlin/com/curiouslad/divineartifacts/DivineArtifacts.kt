package com.curiouslad.divineartifacts

import com.curiouslad.divineartifacts.items.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.BambooBlock
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


@Suppress("UNUSED")
object DivineArtifacts: ModInitializer {
    public const val MOD_ID = "divineartifacts"
    val ITEM_GROUP = FabricItemGroupBuilder.build(
        Identifier("divineartifacts", "general")
    ) {  ItemStack(ModItems.CHANNELING_TABLE) }
    // an instance of our new item


    override fun onInitialize() {

        Registry.register(Registry.ITEM, Identifier(MOD_ID, "divine_core"), ModItems.DIVINE_CORE)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "channeling_table"), ModItems.CHANNELING_TABLE)
       Registry.register(
           Registry.ITEM,
           Identifier(MOD_ID, "channeling_table"),
            BlockItem(ModItems.CHANNELING_TABLE, FabricItemSettings().group(ITEM_GROUP))
       )
    }


}