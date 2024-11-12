package com.adamglin.composeshadow.utils

import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.BlendMode
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

internal actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    this.maskFilter = MaskFilter.makeBlur(
        mode = FilterBlurMode.NORMAL,
        sigma = blurRadius / 2,
        respectCTM = true,
    )
}

internal actual fun NativePaint.setBlendMode(blendMode: BlendMode) {
    this.blendMode = blendMode
}