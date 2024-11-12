package com.adamglin.composeshadow.utils

import androidx.compose.ui.graphics.BlendMode

fun BlendMode.toSkia(): org.jetbrains.skia.BlendMode {
    return when (this) {
        BlendMode.Clear -> org.jetbrains.skia.BlendMode.CLEAR
        BlendMode.Src -> org.jetbrains.skia.BlendMode.SRC
        BlendMode.Dst -> org.jetbrains.skia.BlendMode.DST
        BlendMode.SrcOver -> org.jetbrains.skia.BlendMode.SRC_OVER
        BlendMode.DstOver -> org.jetbrains.skia.BlendMode.DST_OVER
        BlendMode.SrcIn -> org.jetbrains.skia.BlendMode.SRC_IN
        BlendMode.DstIn -> org.jetbrains.skia.BlendMode.DST_IN
        BlendMode.SrcOut -> org.jetbrains.skia.BlendMode.SRC_OUT
        BlendMode.DstOut -> org.jetbrains.skia.BlendMode.DST_OUT
        BlendMode.SrcAtop -> org.jetbrains.skia.BlendMode.SRC_ATOP
        BlendMode.DstAtop -> org.jetbrains.skia.BlendMode.DST_ATOP
        BlendMode.Xor -> org.jetbrains.skia.BlendMode.XOR
        BlendMode.Darken -> org.jetbrains.skia.BlendMode.DARKEN
        BlendMode.Lighten -> org.jetbrains.skia.BlendMode.LIGHTEN
        BlendMode.Multiply -> org.jetbrains.skia.BlendMode.MULTIPLY
        BlendMode.Screen -> org.jetbrains.skia.BlendMode.SCREEN
        BlendMode.Plus -> org.jetbrains.skia.BlendMode.PLUS
        BlendMode.Overlay -> org.jetbrains.skia.BlendMode.OVERLAY
        else -> throw IllegalArgumentException("Not Allow convert BlendMode to PorterDuffMode")
    }
}