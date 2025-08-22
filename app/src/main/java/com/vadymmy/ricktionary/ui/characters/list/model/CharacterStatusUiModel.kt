package com.vadymmy.ricktionary.ui.characters.list.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.theme.AppColors

enum class CharacterStatusUiModel(
    @StringRes
    val stringRes: Int,
    val color: Color
) {
    Alive(stringRes = R.string.character_status_alive, color = AppColors.GreenPrimary),
    Dead(stringRes = R.string.character_status_dead, color = AppColors.Red),
    Unknown(stringRes = R.string.character_status_unknown, color = AppColors.YellowSecondaryDark)
}
