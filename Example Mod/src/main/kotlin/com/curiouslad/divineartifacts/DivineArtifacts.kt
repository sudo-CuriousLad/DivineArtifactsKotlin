package com.curiouslad.divineartifacts

import com.curiouslad.divineartifacts.items.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry


@Suppress("UNUSED")
object DivineArtifacts: ModInitializer {
    public const val MOD_ID = "divineartifacts"

    // an instance of our new item


    override fun onInitialize() {
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "divine_core"), ModItems.DIVINE_CORE)
    }


}