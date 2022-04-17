package com.thebeastshop.beast.theme

import com.thebeastshop.beast.ui.theme.ColorPallet

data class AppThemeState(
    var darkTheme: Boolean = false,
    var pallet: ColorPallet = ColorPallet.GREEN
)