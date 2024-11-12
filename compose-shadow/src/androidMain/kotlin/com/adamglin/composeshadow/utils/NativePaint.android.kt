package com.adamglin.composeshadow.utils

import android.graphics.BlurMaskFilter
import android.graphics.PorterDuffXfermode
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.NativePaint
import org.jetbrains.skia.BlendMode

@RequiresApi(Build.VERSION_CODES.P)
internal actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
}

internal actual fun NativePaint.setBlendMode(blendMode: BlendMode) {
    xfermode = PorterDuffXfermode(blendMode.toPorterDuffMode())
}