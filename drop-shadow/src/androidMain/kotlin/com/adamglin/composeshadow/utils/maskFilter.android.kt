package com.adamglin.composeshadow.utils

import android.graphics.BlurMaskFilter
import androidx.compose.ui.graphics.NativePaint

internal actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    maskFilter = BlurMaskFilter(blurRadius, android.graphics.BlurMaskFilter.Blur.NORMAL)
}