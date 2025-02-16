import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.adamglin.composeshadow.dropShadow
import com.adamglin.composeshadow.innerShadow
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlinx.coroutines.launch
import kotlin.math.pow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleApp() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = 500.dp)
                .fillMaxSize()
                .background(Color.White)
                .safeContentPadding()
                .imePadding()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            var backgroundColor by remember { mutableStateOf(Color.Blue) }
            var theCornerOfShape by remember { mutableStateOf(10f) }
            var shadowColor by remember { mutableStateOf(Color.Black.copy(.2f)) }
            var shadowOffset = remember { Animatable(DpOffset(0.dp, 0.dp), DpOffset.VectorConverter) }
            var shadowBlur by remember { mutableStateOf(4f) }
            var shadowSpread by remember { mutableStateOf(4f) }
            var isShadowInner by remember { mutableStateOf(false) }
            val shape = RoundedCornerShape(theCornerOfShape.dp)
            val coroutineScope = rememberCoroutineScope()
            var isShadowColorBottomSheetShow by remember { mutableStateOf(false) }
            var isBackgroundColorBottomSheetShow by remember { mutableStateOf(false) }

            val offsetIndicatorState = rememberOffsetIndicatorState { offset ->
                val newOffset = DpOffset(
                    x = offset.x.value.truncate(1).dp,
                    y = offset.y.value.truncate(1).dp,
                )
                coroutineScope.launch {
                    shadowOffset.animateTo(newOffset)
                }
            }

            Box(
                modifier = Modifier
                    .padding(50.dp)
                    .align(Alignment.CenterHorizontally)
                    .widthIn(max = 300.dp).fillMaxWidth(.5f).aspectRatio(1f)
                    .clickable { isBackgroundColorBottomSheetShow = true }
                    .shadow(
                        isShadowInner,
                        shape = shape,
                        color = shadowColor,
                        offsetX = shadowOffset.value.x,
                        offsetY = shadowOffset.value.y,
                        blur = shadowBlur.dp,
                        spread = shadowSpread.dp
                    )
                    .background(backgroundColor, shape)
            )

            Column(
                modifier = Modifier.weight(1f).fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                ConsoleItem("is inner") {
                    Switch(checked = isShadowInner, onCheckedChange = { isShadowInner = it })
                }
                ConsoleItem("shape corner $theCornerOfShape") {
                    Slider(
                        value = theCornerOfShape,
                        onValueChange = { coroutineScope.launch { theCornerOfShape = it.truncate(1) } },
                        valueRange = 0f..100f,
                    )
                }

                Row(
                    modifier = Modifier.padding(vertical = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        OffsetItemTextField(
                            value = offsetIndicatorState.targetValue.x.value.truncate(1).toString(),
                            onValueChange = {
                                coroutineScope.launch {
                                    offsetIndicatorState.animateTo(
                                        shadowOffset.value.copy(x = it.dp),
                                    )
                                }
                            },
                            label = "offset x",
                            modifier = Modifier.width(100.dp)
                        )
                        OffsetItemTextField(
                            value = offsetIndicatorState.targetValue.y.value.truncate(1).toString(),
                            onValueChange = {
                                coroutineScope.launch {
                                    offsetIndicatorState.animateTo(
                                        shadowOffset.value.copy(y = it.dp),
                                    )
                                }
                            },
                            label = "offset y",
                            modifier = Modifier.width(100.dp)
                        )
                    }

                    OffsetIndicator(
                        state = offsetIndicatorState,
                        modifier = Modifier.size(120.dp)
                    )

                    ConsoleItem("shadow color") {
                        Box(
                            modifier = Modifier.size(50.dp)
                                .background(shadowColor)
                                .clickable { isShadowColorBottomSheetShow = true }
                        )
                    }
                }

                ConsoleItem("blur $shadowBlur") {
                    Slider(
                        value = shadowBlur,
                        onValueChange = { coroutineScope.launch { shadowBlur = it.truncate(1) } },
                        valueRange = 0f..100f,
                    )
                }

                ConsoleItem("spread $shadowSpread") {
                    Slider(
                        value = shadowSpread,
                        onValueChange = { coroutineScope.launch { shadowSpread = it.truncate(1) } },
                        valueRange = 0f..100f,
                    )
                }
                if (isShadowColorBottomSheetShow) {
                    ModalBottomSheet(onDismissRequest = { isShadowColorBottomSheetShow = false }) {
                        val controller = rememberColorPickerController()
                        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                            HsvColorPicker(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .padding(10.dp),
                                drawDefaultWheelIndicator = true,
                                initialColor = shadowColor,
                                controller = controller,
                                onColorChanged = {
                                    shadowColor = it.color
                                }
                            )
                            AlphaSlider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .height(35.dp),
                                controller = controller,
                            )
                            BrightnessSlider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .height(35.dp),
                                controller = controller,
                            )
                        }
                    }
                }
                if (isBackgroundColorBottomSheetShow) {
                    ModalBottomSheet(onDismissRequest = { isBackgroundColorBottomSheetShow = false }) {
                        val controller = rememberColorPickerController()
                        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                            HsvColorPicker(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(300.dp)
                                    .padding(10.dp),
                                drawDefaultWheelIndicator = true,
                                initialColor = shadowColor,
                                controller = controller,
                                onColorChanged = {
                                    backgroundColor = it.color
                                }
                            )
                            AlphaSlider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .height(35.dp),
                                controller = controller,
                            )
                            BrightnessSlider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                                    .height(35.dp),
                                controller = controller,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ConsoleItem(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Column(modifier) {
        Text(title)
        content()
    }
}

private fun Modifier.shadow(
    isInner: Boolean,
    shape: Shape,
    color: Color,
    offsetX: Dp,
    offsetY: Dp,
    blur: Dp,
    spread: Dp,
): Modifier {
    return if (isInner) {
        this.innerShadow(
            shape = shape,
            color = color,
            offsetX = offsetX,
            offsetY = offsetY,
            blur = blur,
            spread = spread
        )
    } else {
        this.dropShadow(
            shape = shape,
            color = color,
            offsetX = offsetX,
            offsetY = offsetY,
            blur = blur,
            spread = spread
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OffsetItemTextField(
    value: String,
    onValueChange: (Float) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    var innerValue by remember(value) { mutableStateOf(value) }
    val keyboardController = LocalSoftwareKeyboardController.current
    BasicTextField(
        modifier = modifier,
        value = innerValue,
        onValueChange = {
            innerValue = it
            try {
                val float = innerValue.toFloat()
                onValueChange(float)
            } catch (_: NumberFormatException) {}
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        enabled = true,
        singleLine = true,
    ) {
        val mis = remember { MutableInteractionSource() }
        OutlinedTextFieldDefaults.DecorationBox(
            value = value,
            innerTextField = it,
            singleLine = true,
            enabled = true,
            label = { Text(label) },
            visualTransformation = VisualTransformation.None,
            interactionSource = mis,
            colors = OutlinedTextFieldDefaults.colors(),
            contentPadding = PaddingValues(vertical = 2.dp, horizontal = 10.dp),
            container = {
                OutlinedTextFieldDefaults.Container(
                    enabled = true,
                    isError = false,
                    interactionSource = mis,
                    focusedBorderThickness = 1.dp,
                    unfocusedBorderThickness = 1.dp,
                )
            }
        )
    }
}

fun Float.truncate(decimalNumber: Int): Float {
    require(decimalNumber >= 0) { "Decimal number must be non-negative." }
    val multiplier = 10f.pow(decimalNumber)
    return (this * multiplier).toInt() / multiplier
}