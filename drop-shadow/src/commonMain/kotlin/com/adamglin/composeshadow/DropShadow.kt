package com.adamglin.composeshadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
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
import com.adamglin.composeshadow.utils.setMaskFilter

fun Modifier.dropShadow(
    shape: Shape,
    color: Color = Color.Black.copy(0.25f),
    offsetX: Dp = 0.dp,
    offsetY: Dp = 4.dp,
    blur: Dp = 4.dp,
    spread: Dp = 0.dp
): Modifier {
    return this then DropShadowNodeElement(shape, color, offsetX, offsetY, blur, spread)
}

private data class DropShadowNodeElement(
    val shape: Shape,
    val color: Color,
    val offsetX: Dp,
    val offsetY: Dp,
    val blur: Dp,
    val spread: Dp,
) : ModifierNodeElement<DropShadowNode>() {
    override fun create() = DropShadowNode(shape, color, offsetX, offsetY, blur, spread)

    override fun update(node: DropShadowNode) {
        node.shape = shape
        node.color = color
        node.offsetX = offsetX
        node.offsetY = offsetY
        node.blur = blur
        node.spread = spread
    }
}

private class DropShadowNode(
    var shape: Shape,
    var color: Color,
    var offsetX: Dp,
    var offsetY: Dp,
    var blur: Dp,
    var spread: Dp,
) : DrawModifierNode, Modifier.Node() {
    override fun ContentDrawScope.draw() {
        val shadowSize = Size(size.width + spread.toPx(), size.height + spread.toPx())
        val shadowOutline = shape.createOutline(shadowSize, layoutDirection, this)
        val rOffsetX = offsetX.toPx() - spread.toPx() / 2
        val rOffsetY = offsetY.toPx() - spread.toPx() / 2
        val paint = Paint()
        paint.color = color
        if (blur.value > 0) {
            paint.asFrameworkPaint().apply {
                setMaskFilter(blur.toPx())
            }
        }
        drawIntoCanvas { canvas ->
            // Save the canvas state
            canvas.save()
            // Translate to specified offsets
            canvas.translate(rOffsetX, rOffsetY)
            // Draw the shadow
            canvas.drawOutline(shadowOutline, paint)
            // Restore the canvas state
            canvas.restore()
        }
        drawContent()
    }
}