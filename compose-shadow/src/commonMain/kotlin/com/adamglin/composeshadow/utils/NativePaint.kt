package com.adamglin.composeshadow.utils

import androidx.compose.ui.graphics.NativePaint

internal expect fun NativePaint.setMaskFilter(blurRadius: Float)
internal expect fun NativePaint.setBlendMode(blendMode: BlendMode)
