package com.adamglin.composeshadow.utils

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.FilterBlurMode
import org.jetbrains.skia.MaskFilter
import org.jetbrains.skia.BlendMode as SkiaBlendMode

internal actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    this.maskFilter = MaskFilter.makeBlur(
        mode = FilterBlurMode.NORMAL,
        sigma = blurRadius / 2,
        respectCTM = true,
    )
}

internal actual fun NativePaint.setBlendMode(blendMode: BlendMode) {
    this.blendMode = blendMode.toSkiaBlendMode()
}

private fun BlendMode.toSkiaBlendMode(): SkiaBlendMode {
    return when (this) {
        BlendMode.Clear -> SkiaBlendMode.CLEAR
        BlendMode.Src -> SkiaBlendMode.SRC
        BlendMode.Dst -> SkiaBlendMode.DST
        BlendMode.SrcOver -> SkiaBlendMode.SRC_OVER
        BlendMode.DstOver -> SkiaBlendMode.DST_OVER
        BlendMode.SrcIn -> SkiaBlendMode.SRC_IN
        BlendMode.DstIn -> SkiaBlendMode.DST_IN
        BlendMode.SrcOut -> SkiaBlendMode.SRC_OUT
        BlendMode.DstOut -> SkiaBlendMode.DST_OUT
        BlendMode.SrcAtop -> SkiaBlendMode.SRC_ATOP
        BlendMode.DstAtop -> SkiaBlendMode.DST_ATOP
        BlendMode.Xor -> SkiaBlendMode.XOR
        BlendMode.Darken -> SkiaBlendMode.DARKEN
        BlendMode.Lighten -> SkiaBlendMode.LIGHTEN
        BlendMode.Multiply -> SkiaBlendMode.MULTIPLY
        BlendMode.Screen -> SkiaBlendMode.SCREEN
        BlendMode.Plus -> SkiaBlendMode.PLUS
        BlendMode.Overlay -> SkiaBlendMode.OVERLAY
        else -> throw IllegalArgumentException("Not Allow convert BlendMode to PorterDuffMode")
    }
}
