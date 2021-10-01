package com.curiouslad.divineartifacts.items
import com.curiouslad.divineartifacts.DivineArtifacts
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object ModItems {
    val DIVINE_CORE = Item(FabricItemSettings().group(ItemGroup.MISC))
    val MURASAMA = Item(FabricItemSettings().group(ItemGroup.COMBAT))

    fun registerItems() {


    }

}
