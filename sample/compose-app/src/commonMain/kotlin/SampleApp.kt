import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.adamglin.composeshadow.dropShadow
import com.adamglin.composeshadow.innerShadow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun SampleApp() {
    LazyColumn {
        item {
            Column {
                SecondTitle("Different Sizes")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    repeat(5) {
                        val size = it * 2
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .dropShadow(
                                    shape = RectangleShape,
                                    offsetX = size.dp,
                                    offsetY = size.dp,
                                )
                                .background(Color.White),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text("$size × $size", fontSize = 10.sp)
                        }
                    }
                }
            }
        }
        item {
            Column {
                SecondTitle("Different Shapes")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .dropShadow(shape = RectangleShape)
                            .background(Color.White),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Rect", fontSize = 10.sp)
                    }
                    val round = RoundedCornerShape(10.dp)
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .dropShadow(shape = round)
                            .clip(round)
                            .background(Color.White, shape = round),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Round", fontSize = 10.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .dropShadow(shape = CircleShape)
                            .clip(CircleShape)
                            .background(Color.White, shape = CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Round", fontSize = 10.sp)
                    }
                }
            }
        }
        item {
            Column {
                SecondTitle("Different Colors")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .dropShadow(shape = CircleShape, color = Color.Red)
                            .clip(CircleShape)
                            .background(Color.White, shape = CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Red", fontSize = 10.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .dropShadow(shape = CircleShape, color = Color.Green)
                            .clip(CircleShape)
                            .background(Color.White, shape = CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Green", fontSize = 10.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .dropShadow(shape = CircleShape, color = Color.Blue)
                            .clip(CircleShape)
                            .background(Color.White, shape = CircleShape),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("Blue", fontSize = 10.sp)
                    }
                    AnimationColorfulCircleBox()
                }
            }
        }
        item {
            Column {
                SecondTitle("Different Spread")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    repeat(5) {
                        val size = it * 2
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .dropShadow(
                                    shape = RectangleShape,
                                    offsetX = 0.dp,
                                    offsetY = 0.dp,
                                    spread = size.dp,
                                )
                                .background(Color.White),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text("$size", fontSize = 10.sp)
                        }
                    }
                }
            }
        }
        item {
            Column {
                SecondTitle("Different Blur")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    repeat(5) {
                        val size = it * 2
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .dropShadow(
                                    shape = RectangleShape,
                                    offsetX = 0.dp,
                                    offsetY = 0.dp,
                                    blur = size.dp,
                                )
                                .background(Color.White),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text("$size", fontSize = 10.sp)
                        }
                    }
                }
            }
        }
        item {
            Column {
                SecondTitle("Inner Shadow")
                Row(
                    modifier = Modifier.horizontalScroll(rememberScrollState()).padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .innerShadow(
                                shape = RectangleShape,
                                color = Color.Red,
                                offsetX = 0.dp,
                                offsetY = 0.dp,
                                blur = 2.dp,
                                spread = 0.dp,
                            )
                            .background(Color.White),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("blur 2", fontSize = 10.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.White)
                            .innerShadow(
                                shape = RectangleShape,
                                color = Color.Red,
                                offsetX = 5.dp,
                                offsetY = 5.dp,
                                blur = 4.dp,
                                spread = 4.dp,
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("size 5", fontSize = 10.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.White, CircleShape)
                            .innerShadow(
                                shape = CircleShape,
                                color = Color.Red,
                                offsetX = 5.dp,
                                offsetY = 5.dp,
                                blur = 4.dp,
                                spread = 4.dp,
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("circle", fontSize = 10.sp)
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color.White, CircleShape)
                            .innerShadow(
                                shape = CircleShape,
                                color = Color.Red,
                                offsetX = (-5).dp,
                                offsetY = (-5).dp,
                                blur = 4.dp,
                                spread = 4.dp,
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("size-5", fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun SecondTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(text, modifier = modifier, fontSize = 24.sp, fontWeight = FontWeight.Medium)
}

@Composable
fun AnimationColorfulCircleBox(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()
    val x0 = remember { Animatable(0f) }
    val y0 = remember { Animatable(0f) }
    val x1 = remember { Animatable(0f) }
    val y1 = remember { Animatable(0f) }
    val x2 = remember { Animatable(0f) }
    val y2 = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        launch {
            while (true) {
                val d = (1000..3000).random()
                x0.animateTo((-5..5).random().toFloat(), animationSpec = tween(d))
                delay(d.toLong())
            }
        }
        launch {
            while (true) {
                val d = (1000..3000).random()
                x1.animateTo((-5..5).random().toFloat(), animationSpec = tween(d))
                delay(d.toLong())
            }
        }
        launch {
            while (true) {
                val d = (1000..3000).random()
                x2.animateTo((-5..5).random().toFloat(), animationSpec = tween(d))
                delay(d.toLong())
            }
        }
        launch {
            while (true) {
                val d = (1000..3000).random()
                y0.animateTo((-5..5).random().toFloat(), animationSpec = tween(d))
                delay(d.toLong())
            }
        }
        launch {
            while (true) {
                val d = (1000..3000).random()
                y1.animateTo((-5..5).random().toFloat(), animationSpec = tween(d))
                delay(d.toLong())
            }
        }
        launch {
            while (true) {
                val d = (1000..3000).random()
                y2.animateTo((-5..5).random().toFloat(), animationSpec = tween(d))
                delay(d.toLong())
            }
        }
    }
    Box(
        modifier = Modifier
            .size(50.dp)
            .dropShadow(shape = CircleShape, color = Color(0xff5869C1), offsetX = 1.dp, offsetY = 3.dp)
            .dropShadow(
                shape = CircleShape,
                color = Color(0xff11BFA5),
                offsetX = x0.value.dp,
                offsetY = y0.value.dp
            )
            .dropShadow(
                shape = CircleShape,
                color = Color(0xffFFF6E9),
                offsetX = x1.value.dp,
                offsetY = y1.value.dp
            )
            .dropShadow(
                shape = CircleShape,
                color = Color(0xffD8FFE8),
                offsetX = x2.value.dp,
                offsetY = y2.value.dp
            )
            .clip(CircleShape)
            .background(Color.White, shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text("Complex", fontSize = 10.sp)
    }
}

@Preview
@Composable
private fun SampleAppPreview() {
    Box(modifier = Modifier.padding(40.dp)) {
        SampleApp()
    }
}