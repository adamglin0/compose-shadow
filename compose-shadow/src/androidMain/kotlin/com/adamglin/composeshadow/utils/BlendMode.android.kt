package com.adamglin.composeshadow.utils

import android.graphics.PorterDuff
import androidx.compose.ui.graphics.BlendMode

fun BlendMode.toPorterDuffMode(): PorterDuff.Mode {
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