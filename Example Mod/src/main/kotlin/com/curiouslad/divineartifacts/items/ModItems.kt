package com.curiouslad.divineartifacts.items

import com.curiouslad.divineartifacts.DivineArtifacts.ITEM_GROUP
import com.curiouslad.divineartifacts.classes.BlessedContainer
import com.curiouslad.divineartifacts.classes.BlessedContainerEntity
import com.curiouslad.divineartifacts.classes.ChannelingTable
import com.curiouslad.divineartifacts.classes.ChannelingTableEntity
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.item.Item


object ModItems {
    val DIVINE_CORE = Item(FabricItemSettings().group(ITEM_GROUP))

    val CHANNELING_TABLE = ChannelingTable()
    var CHANNELING_TABLE_ENTITY: BlockEntityType<ChannelingTableEntity>? = null
    val BLESSED_CONTAINER = BlessedContainer()
    var BLESSED_CONTAINER_ENTITY: BlockEntityType<BlessedContainerEntity>? = null




}
