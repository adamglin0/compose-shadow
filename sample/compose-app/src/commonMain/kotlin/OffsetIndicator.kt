import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Composable
fun rememberOffsetIndicatorState(
    onValueChange: (DpOffset) -> Unit,
): OffsetIndicatorState {
    val onValueChangeState = rememberUpdatedState(onValueChange)
    return remember {
        OffsetIndicatorState(
            onValueChange = { onValueChangeState.value.invoke(it) }
        )
    }
}

class OffsetIndicatorState(
    val onValueChange: (DpOffset) -> Unit,
) {
    private val animatable = Animatable(DpOffset.Zero, DpOffset.VectorConverter)
    val targetValue get() = animatable.targetValue


    suspend fun snapTo(offset: DpOffset) {
        animatable.snapTo(offset)
        onValueChange.invoke(offset)
    }

    suspend fun animateTo(offset: DpOffset) {
        onValueChange.invoke(offset)
        animatable.animateTo(offset, spring(stiffness = Spring.StiffnessHigh))
    }
}

private val DpOffset.VectorConverter: TwoWayConverter<Offset, AnimationVector2D>
    get() = TwoWayConverter(
        convertToVector = { AnimationVector2D(it.x, it.y) },
        convertFromVector = { Offset(it.v1, it.v2) }
    )


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OffsetIndicator(
    state: OffsetIndicatorState,
    modifier: Modifier = Modifier
) {
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()
    Box(modifier) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize()
                .border(1.dp, color = OFFSET_INDICATOR_LINE_COLOR)
                .drawBehind {
                    drawLine(
                        OFFSET_INDICATOR_LINE_COLOR,
                        strokeWidth = .5f,
                        start = size.TopCenterOffset,
                        end = size.BottomCenterOffset
                    )
                    drawLine(
                        OFFSET_INDICATOR_LINE_COLOR,
                        strokeWidth = .5f,
                        start = size.CenterLeftOffset,
                        end = size.CenterRightOffset,
                    )
                }
        ) {
            val boxWidth = maxWidth.value.dp

            val realOffset =
                DpOffset(
                    state.targetValue.x + boxWidth / 2 - MOVABLE_POINTER_SIZE / 2,
                    state.targetValue.y + boxWidth / 2 - MOVABLE_POINTER_SIZE / 2
                )
            val draggable2DState = rememberDraggable2DState {
                with(density) {
                    DpOffset(
                        (state.targetValue.x + it.x.toDp()).coerceIn(-boxWidth / 2, boxWidth / 2),
                        (state.targetValue.y + it.y.toDp()).coerceIn(-boxWidth / 2, boxWidth / 2),
                    ).let {
                        println("${state.targetValue}result $it")
                        coroutineScope.launch { state.snapTo(it) }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .offset(realOffset.x, realOffset.y)
                    .size(MOVABLE_POINTER_SIZE)
                    .clip(CircleShape)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .background(Color.Blue)
                    .draggable2D(draggable2DState)
            )
        }
    }
}

private val OFFSET_INDICATOR_LINE_COLOR = Color.LightGray
private val MOVABLE_POINTER_SIZE = 10.dp

private val Size.TopLeftOffset: Offset
    get() = Offset.Zero

private val Size.TopCenterOffset: Offset
    get() = Offset(width / 2, 0f)

private val Size.TopRightOffset: Offset
    get() = Offset(width, 0f)

private val Size.CenterLeftOffset: Offset
    get() = Offset(0f, height / 2)

private val Size.CenterOffset: Offset
    get() = Offset(width / 2, height / 2)

private val Size.CenterRightOffset: Offset
    get() = Offset(width, height / 2)

private val Size.BottomLeftOffset: Offset
    get() = Offset(0f, height)

private val Size.BottomCenterOffset: Offset
    get() = Offset(width / 2, height)

private val Size.BottomRightOffset: Offset
    get() = Offset(width, height)