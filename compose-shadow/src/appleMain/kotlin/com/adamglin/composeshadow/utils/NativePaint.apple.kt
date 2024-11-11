package com.adamglin.composeshadow.utils

import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter

internal actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    this.maskFilter = MaskFilter.makeBlur(FilterBlurMode.NORMAL, blurRadius / 2, true)
}

internal actual fun NativePaint.setBlendMode(blendMode: BlendMode) {
    this.blendMode = when (blendMode) {
        BlendMode.CLEAR -> org.jetbrains.skia.BlendMode.CLEAR
        BlendMode.SRC -> org.jetbrains.skia.BlendMode.SRC
        BlendMode.DST -> org.jetbrains.skia.BlendMode.DST
        BlendMode.SRC_OVER -> org.jetbrains.skia.BlendMode.SRC_OVER
        BlendMode.DST_OVER -> org.jetbrains.skia.BlendMode.DST_OVER
        BlendMode.SRC_IN -> org.jetbrains.skia.BlendMode.SRC_IN
        BlendMode.DST_IN -> org.jetbrains.skia.BlendMode.DST_IN
        BlendMode.SRC_OUT -> org.jetbrains.skia.BlendMode.SRC_OUT
        BlendMode.DST_OUT -> org.jetbrains.skia.BlendMode.DST_OUT
        BlendMode.SRC_ATOP -> org.jetbrains.skia.BlendMode.SRC_ATOP
        BlendMode.DST_ATOP -> org.jetbrains.skia.BlendMode.DST_ATOP
        BlendMode.XOR -> org.jetbrains.skia.BlendMode.XOR
        BlendMode.DARKEN -> org.jetbrains.skia.BlendMode.DARKEN
        BlendMode.LIGHTEN -> org.jetbrains.skia.BlendMode.LIGHTEN
        BlendMode.MULTIPLY -> org.jetbrains.skia.BlendMode.MULTIPLY
        BlendMode.SCREEN -> org.jetbrains.skia.BlendMode.SCREEN
        BlendMode.ADD -> org.jetbrains.skia.BlendMode.PLUS
        BlendMode.OVERLAY -> org.jetbrains.skia.BlendMode.OVERLAY
    }
}