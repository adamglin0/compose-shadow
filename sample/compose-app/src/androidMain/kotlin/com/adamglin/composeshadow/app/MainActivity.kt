package com.adamglin.composeshadow.app

import SampleApp
import android.graphics.drawable.shapes.Shape
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.adamglin.composeshadow.SoftLayerShadowContainer
import com.adamglin.composeshadow.softLayerShadow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoftLayerShadowContainer{
                Column {

                    Box(
                        modifier = Modifier
                            .padding(40.dp)
                            .softLayerShadow(
                                shape = RoundedCornerShape(4.dp)
                            )
                            .requiredSize(100.dp)
                            .background(Color.White)
                    )
                    SampleApp()
                }
            }
        }
    }
}

