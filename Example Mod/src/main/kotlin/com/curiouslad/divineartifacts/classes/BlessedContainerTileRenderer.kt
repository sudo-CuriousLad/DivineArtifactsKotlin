package com.curiouslad.divineartifacts.classes

import net.minecraft.block.entity.BlockEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import software.bernie.geckolib3.model.AnimatedGeoModel
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer


class BlessedContainerTileRenderer : GeoBlockRenderer<BlessedContainerEntity>(BlessedContainerModel()), BlockEntityRenderer<BlockEntity> {
     override fun getRenderType(
        animatable: BlessedContainerEntity, partialTicks: Float, stack: MatrixStack,
        renderTypeBuffer: VertexConsumerProvider, vertexBuilder: VertexConsumer, packedLightIn: Int,
        textureLocation: Identifier
    ): RenderLayer {
        return RenderLayer.getEntityTranslucent(getTextureLocation(animatable))
    }
}


