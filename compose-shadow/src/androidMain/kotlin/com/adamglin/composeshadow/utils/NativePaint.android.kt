package com.adamglin.composeshadow.utils

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.NativePaint

@RequiresApi(Build.VERSION_CODES.P)
internal actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
}

internal actual fun NativePaint.setBlendMode(blendMode: BlendMode) {
    xfermode = PorterDuffXfermode(blendMode.toPorterDuffMode())
}

private fun BlendMode.toPorterDuffMode(): PorterDuff.Mode {
    return when (this) {
        BlendMode.Clear -> PorterDuff.Mode.CLEAR
        BlendMode.Src -> PorterDuff.Mode.SRC
        BlendMode.Dst -> PorterDuff.Mode.DST
        BlendMode.SrcOver -> PorterDuff.Mode.SRC_OVER
        BlendMode.DstOver -> PorterDuff.Mode.DST_OVER
        BlendMode.SrcIn -> PorterDuff.Mode.SRC_IN
        BlendMode.DstIn -> PorterDuff.Mode.DST_IN
        BlendMode.SrcOut -> PorterDuff.Mode.SRC_OUT
        BlendMode.DstOut -> PorterDuff.Mode.DST_OUT
        BlendMode.SrcAtop -> PorterDuff.Mode.SRC_ATOP
        BlendMode.DstAtop -> PorterDuff.Mode.DST_ATOP
        BlendMode.Xor -> PorterDuff.Mode.XOR
        BlendMode.Darken -> PorterDuff.Mode.DARKEN
        BlendMode.Lighten -> PorterDuff.Mode.LIGHTEN
        BlendMode.Multiply -> PorterDuff.Mode.MULTIPLY
        BlendMode.Screen -> PorterDuff.Mode.SCREEN
        BlendMode.Plus -> PorterDuff.Mode.ADD
        BlendMode.Overlay -> PorterDuff.Mode.OVERLAY
        else -> throw IllegalArgumentException("Not Allow convert BlendMode to PorterDuffMode")
    }
}
