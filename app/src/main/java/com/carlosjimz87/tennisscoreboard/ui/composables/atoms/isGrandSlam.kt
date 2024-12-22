package com.carlosjimz87.tennisscoreboard.ui.composables.atoms

import com.carlosjimz87.tennisscoreboard.BuildConfig

@Suppress("ConstantConditionIf")
fun isGrandSlam(): Boolean {
    return BuildConfig.MAX_SETS > 3
}