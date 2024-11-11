package com.adamglin.composeshadow

import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adamglin.composeshadow.utils.BlendMode
import com.adamglin.composeshadow.utils.setBlendMode
import com.adamglin.composeshadow.utils.setMaskFilter

@RequiresApi(26)
fun Modifier.innerShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.25f),
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp,
    blur: Dp = 4.dp,
    spread: Dp = 0.dp,
): Modifier {
    return this then InnerShadowNodeElement(shape, color, offsetX, offsetY, blur, spread)
}

private data class InnerShadowNodeElement(
    val shape: Shape,
    val color: Color,
    val offsetX: Dp,
    val offsetY: Dp,
    val blur: Dp,
    val spread: Dp,
) : ModifierNodeElement<InnerShadowNode>() {
    override fun create() = InnerShadowNode(shape, color, offsetX, offsetY, blur, spread)

    override fun update(node: InnerShadowNode) {
        node.shape = shape
        node.color = color
        node.offsetX = offsetX
        node.offsetY = offsetY
        node.blur = blur
        node.spread = spread
    }
}

private class InnerShadowNode(
    var shape: Shape,
    var color: Color,
    var offsetX: Dp,
    var offsetY: Dp,
    var blur: Dp,
    var spread: Dp,
) : DrawModifierNode, Modifier.Node() {
    override fun ContentDrawScope.draw() {
        drawContent()

        val rect = Rect(Offset.Zero, size)
        val paint = Paint().apply {
            color = this@InnerShadowNode.color
            isAntiAlias = true
        }

        val shadowOutline = shape.createOutline(size, layoutDirection, this)


        drawIntoCanvas { canvas ->
            canvas.saveLayer(rect, paint)
            canvas.drawOutline(shadowOutline, paint)

            paint.asFrameworkPaint()
            val frameworkPaint = paint.asFrameworkPaint()
            frameworkPaint.setBlendMode(BlendMode.DST_OUT)
            if (blur.toPx() > 0) {
                frameworkPaint.setMaskFilter(blur.toPx())
            }
            paint.color = Color.Black

            val spreadOffsetX = offsetX.toPx() + if (offsetX.toPx() < 0) -spread.toPx() else spread.toPx()
            val spreadOffsetY = offsetY.toPx() + if (offsetY.toPx() < 0) -spread.toPx() else spread.toPx()

            canvas.translate(spreadOffsetX, spreadOffsetY)
            canvas.drawOutline(shadowOutline, paint)
            canvas.restore()
        }
    }
}
