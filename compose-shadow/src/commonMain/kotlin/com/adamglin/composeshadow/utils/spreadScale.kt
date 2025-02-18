package com.adamglin.composeshadow.utils

/**
 * Calculates shadow spread scale.
 *
 * @param spread The raw spread.
 * @param size The X or Y side.
 * @return The shadow spread scale.
 * @see com.gigamole.composeshadowsplus.rsblur.rsBlurShadow
 * @see com.gigamole.composeshadowsplus.softlayer.softLayerShadow
 * @author GIGAMOLE
 */
internal fun spreadScale(
    spread: Float,
    size: Float
): Float = 1.0F + ((spread / size) * 2.0F)
