package com.adamglin.composeshadow

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.adamglin.composeshadow.utils.spreadScale


/**
 * A [NativePaint.setShadowLayer]/[View.LAYER_TYPE_SOFTWARE] layer type based shadow [Modifier].
 *
 * You must use it with [SoftLayerShadowContainer].
 *
 * @param radius The shadow radius.
 * @param color The shadow color.
 * @param shape The shadow shape.
 * @param spread The shadow spread.
 * @param offset The shadow offset.
 * @param isAlphaContentClip Indicates whether the alpha (transparent) content should be clipped to the [shape].
 * @return The applied SoftLayerShadow [Modifier].
 * @see SoftLayerShadowContainer
 * @author GIGAMOLE
 */
fun Modifier.softLayerShadow(
    shape: Shape,
    radius: Dp = 4.dp,
    color: Color = Color.Black.copy(0.25f),
    spread: Dp = 4.dp,
    offset: DpOffset = DpOffset(4.dp, 4.dp),
): Modifier = this.drawWithCache {
    val radiusPx = radius.toPx()
    val isRadiusValid = radiusPx > 0.0F
    val paint = Paint().apply {
        this.color = if (isRadiusValid) {
            Color.Transparent
        } else {
            color
        }

        asFrameworkPaint().apply {
            isDither = true
            isAntiAlias = true

            if (isRadiusValid) {
                setShadowLayer(
                    radiusPx,
                    offset.x.toPx(),
                    offset.y.toPx(),
                    color.toArgb()
                )
            }
        }
    }
    val shapeOutline = shape.createOutline(
        size = size,
        layoutDirection = LayoutDirection.Rtl,
        density = this
    )
    val shapePath = Path().apply {
        addOutline(outline = shapeOutline)
    }

    val drawShadowBlock: DrawScope.() -> Unit = {
        drawIntoCanvas { canvas ->
            canvas.withSave {
                if (isRadiusValid.not()) {
                    canvas.translate(
                        dx = offset.x.toPx(),
                        dy = offset.y.toPx()
                    )
                }

                if (spread.value != 0.0F) {
                    canvas.scale(
                        sx = spreadScale(
                            spread = spread.toPx(),
                            size = size.width
                        ),
                        sy = spreadScale(
                            spread = spread.toPx(),
                            size = size.height
                        ),
                        pivotX = center.x,
                        pivotY = center.y
                    )
                }

                canvas.drawOutline(
                    outline = shapeOutline,
                    paint = paint
                )
            }
        }
    }

    onDrawBehind {
        drawShadowBlock()
    }
}
