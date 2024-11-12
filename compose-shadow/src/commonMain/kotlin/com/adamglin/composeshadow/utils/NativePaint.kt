package com.adamglin.composeshadow.utils

import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.BlendMode

internal expect fun NativePaint.setMaskFilter(blurRadius: Float)
internal expect fun NativePaint.setBlendMode(blendMode: BlendMode)
