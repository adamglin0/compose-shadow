import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.adamglin.composeshadow.dropShadow

@Composable
fun SampleApp() {
    val safePaddings = WindowInsets.safeContent.asPaddingValues()
    Column(
        modifier = Modifier
            .background(Color.Magenta.copy(.1f))
            .padding(safePaddings)
            .padding(horizontal = 20.dp)
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .background(Color.Red.copy(.1f))
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(20.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(30.dp),
            userScrollEnabled = true,
        ) {
            item {
                BoxWithShadow(
                    shape = RectangleShape,
                    color = Color.Black.copy(alpha = 0.25f),
                    offsetX = 4.dp,
                    offsetY = 4.dp,
                    blur = 4.dp,
                    spread = 4.dp,
                )
            }
            item {
                BoxWithShadow(
                    shape = CircleShape,
                    color = Color.Red,
                    offsetX = 4.dp,
                    offsetY = 4.dp,
                    blur = 4.dp,
                    spread = 4.dp,
                )
            }
            item {
                BoxWithShadow(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.Black.copy(alpha = 0.5f),
                    offsetX = (-4).dp,
                    offsetY = (-4).dp,
                    blur = 4.dp,
                    spread = 4.dp,
                )
            }
            item {
                BoxWithShadow(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.Black.copy(alpha = 0.5f),
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    blur = 4.dp,
                    spread = 4.dp,
                )
            }
            item {
                BoxWithShadow(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.Black.copy(alpha = 0.5f),
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    blur = 0.dp,
                    spread = 0.dp,
                )
            }
            item {
                BoxWithShadow(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.Black.copy(alpha = 0.5f),
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    blur = 10.dp,
                    spread = 0.dp,
                )
            }
            item {
                BoxWithShadow(
                    shape = RoundedCornerShape(20.dp),
                    color = Color.Black.copy(alpha = 0.5f),
                    offsetX = 0.dp,
                    offsetY = 0.dp,
                    blur = 0.dp,
                    spread = 10.dp,
                )
            }
            item {
                Box(
                    Modifier.fillMaxWidth().aspectRatio(1f)
                        .dropShadow(
                            shape = RectangleShape,
                            color = Color.Black.copy(.1f),
                            offsetX = 4.dp,
                            offsetY = 4.dp,
                            blur = 4.dp,
                            spread = 4.dp,
                        )
                        .dropShadow(
                            shape = RectangleShape,
                            color = Color.Red.copy(.6f),
                            offsetX = 5.dp,
                            offsetY = (-8).dp,
                            blur = 4.dp,
                            spread = 4.dp,
                        )
                        .dropShadow(
                            shape = RectangleShape,
                            color = Color.Blue.copy(.2f),
                            offsetX = 8.dp,
                            offsetY = 8.dp,
                            blur = 8.dp,
                            spread = 8.dp,
                        )
                        .background(Color.White, RectangleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    BasicText("4,4,4,4")
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        Column {
            var offsetX by remember { mutableStateOf(0f) }
            var offsetY by remember { mutableStateOf(0f) }
            var blur by remember { mutableStateOf(4f) }
            var spread by remember { mutableStateOf(4f) }
            Box(
                Modifier
                    .dropShadow(
                        shape = RectangleShape,
                        color = Color.Black.copy(.5f),
                        offsetX = offsetX.dp,
                        offsetY = offsetY.dp,
                        blur = blur.dp,
                        spread = spread.dp,
                    )
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(Color.White.copy(.5f), RectangleShape),
                contentAlignment = Alignment.Center,
            ) {
                Text("$offsetX,$offsetY,$blur,$spread")
            }
            Slider(offsetX, onValueChange = { offsetX = it }, valueRange = -20f..20f)
            Slider(offsetY, onValueChange = { offsetY = it }, valueRange = -20f..20f)
            Slider(blur, onValueChange = { blur = it }, valueRange = 0f..20f)
            Slider(spread, onValueChange = { spread = it }, valueRange = -20f..20f)
        }
    }
}

@Composable
fun BoxWithShadow(
    shape: Shape,
    color: Color,
    offsetX: Dp,
    offsetY: Dp,
    blur: Dp,
    spread: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier
            .fillMaxWidth().aspectRatio(1f)
            .dropShadow(
                shape = shape,
                color = color,
                offsetX = offsetX,
                offsetY = offsetY,
                blur = blur,
                spread = spread,
            )
            .background(Color.White, shape),
        contentAlignment = Alignment.Center,
    ) {
        Text("${offsetX.value},${offsetY.value},${blur.value},${spread.value}")
    }
}