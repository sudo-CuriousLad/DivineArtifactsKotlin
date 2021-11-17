package com.curiouslad.divineartifacts

import com.curiouslad.divineartifacts.classes.BlessedContainerEntity
import com.curiouslad.divineartifacts.classes.BlessedContainerTileRenderer
import com.curiouslad.divineartifacts.classes.ChannelingTableEntity
import com.curiouslad.divineartifacts.items.ModItems
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.entity.FabricBlockEntityTypeBuilder
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import software.bernie.example.block.tile.BotariumTileEntity
import software.bernie.example.client.renderer.tile.BotariumTileRenderer
import software.bernie.example.registry.TileRegistry


@Suppress("UNUSED")
object DivineArtifacts: ModInitializer {
    const val MOD_ID = "divineartifacts"
    val ITEM_GROUP = FabricItemGroupBuilder.build(
        Identifier("divineartifacts", "general")
    ) {  ItemStack(ModItems.CHANNELING_TABLE) }!!
    // an instance of our new item


    override fun onInitialize() {

        Registry.register(Registry.ITEM, Identifier(MOD_ID, "divine_core"), ModItems.DIVINE_CORE)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "channeling_table"), ModItems.CHANNELING_TABLE)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "blessed_container"), ModItems.BLESSED_CONTAINER)
       Registry.register(
           Registry.ITEM,
           Identifier(MOD_ID, "channeling_table"),
            BlockItem(ModItems.CHANNELING_TABLE, FabricItemSettings().group(ITEM_GROUP))
       )
        Registry.register(
            Registry.ITEM,
            Identifier(MOD_ID, "blessed_container"),
            BlockItem(ModItems.BLESSED_CONTAINER, FabricItemSettings().group(ITEM_GROUP))
        )

        Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            "divineartifacts:blessed_container_entity",
            FabricBlockEntityTypeBuilder.create({ _, _ ->  BlessedContainerEntity(pos = null, state = null)} , ModItems.BLESSED_CONTAINER).build(null)
        )
        net.fabricmc.fabric.api.client.rendereregistry.v1.BlockEntityRendererRegistry.INSTANCE.register<BlessedContainerEntity>(ModItems.BLESSED_CONTAINER_ENTITY,
            BlockEntityRendererFactory<BlessedContainerEntity> { rendererDispatcherIn: BlockEntityRendererFactory.Context? -> BlessedContainerTileRenderer() })
        Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            "divineartifacts:channeling_table_entity",
            FabricBlockEntityTypeBuilder.create({ _, _ ->  ChannelingTableEntity(pos = null, state = null)} , ModItems.CHANNELING_TABLE).build(null)
        )
    }


}