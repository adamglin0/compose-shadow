package com.adamglin.composeshadow.utils

import android.graphics.PorterDuff
import org.jetbrains.skia.BlendMode

fun BlendMode.toPorterDuffMode(): PorterDuff.Mode {
    return when (this) {
        BlendMode.CLEAR -> PorterDuff.Mode.CLEAR
        BlendMode.SRC -> PorterDuff.Mode.SRC
        BlendMode.DST -> PorterDuff.Mode.DST
        BlendMode.SRC_OVER -> PorterDuff.Mode.SRC_OVER
        BlendMode.DST_OVER -> PorterDuff.Mode.DST_OVER
        BlendMode.SRC_IN -> PorterDuff.Mode.SRC_IN
        BlendMode.DST_IN -> PorterDuff.Mode.DST_IN
        BlendMode.SRC_OUT -> PorterDuff.Mode.SRC_OUT
        BlendMode.DST_OUT -> PorterDuff.Mode.DST_OUT
        BlendMode.SRC_ATOP -> PorterDuff.Mode.SRC_ATOP
        BlendMode.DST_ATOP -> PorterDuff.Mode.DST_ATOP
        BlendMode.XOR -> PorterDuff.Mode.XOR
        BlendMode.DARKEN -> PorterDuff.Mode.DARKEN
        BlendMode.LIGHTEN -> PorterDuff.Mode.LIGHTEN
        BlendMode.MULTIPLY -> PorterDuff.Mode.MULTIPLY
        BlendMode.SCREEN -> PorterDuff.Mode.SCREEN
        BlendMode.PLUS -> PorterDuff.Mode.ADD
        BlendMode.OVERLAY -> PorterDuff.Mode.OVERLAY
        else -> throw IllegalArgumentException("Not Allow convert BlendMode to PorterDuffMode")
    }
}