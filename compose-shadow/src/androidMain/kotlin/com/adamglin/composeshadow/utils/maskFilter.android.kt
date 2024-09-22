package com.adamglin.composeshadow.utils

import android.graphics.BlurMaskFilter
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.graphics.NativePaint

@RequiresApi(Build.VERSION_CODES.P)
internal actual fun NativePaint.setMaskFilter(blurRadius: Float) {
    maskFilter = BlurMaskFilter(blurRadius, BlurMaskFilter.Blur.NORMAL)
}