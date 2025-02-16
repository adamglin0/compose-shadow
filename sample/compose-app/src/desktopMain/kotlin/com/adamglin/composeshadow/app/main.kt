package com.adamglin.composeshadow.app

import SampleApp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.window.singleWindowApplication

fun main() {
    application {
        val windowState = rememberWindowState(width = 360.dp, height = 700.dp)
        Window(state = windowState, onCloseRequest = ::exitApplication) {
            SampleApp()
        }
    }
}