package com.vadymmy.ricktionary.ui.characters.common.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.vadymmy.ricktionary.R
import com.vadymmy.ricktionary.ui.theme.AppColors

enum class CharacterGenderUiModel(
    @StringRes
    val stringRes: Int,
    @DrawableRes
    val iconRes: Int,
    val color: Color
) {
    Male(stringRes = R.string.character_gender_male, iconRes = R.drawable.ic_male, color = AppColors.Blue),
    Female(stringRes = R.string.character_gender_female, iconRes = R.drawable.ic_female, color = AppColors.Pink),
    Genderless(stringRes = R.string.character_gender_genderless, iconRes = R.drawable.ic_genderless, color = AppColors.Purple),
    Unknown(stringRes = R.string.unknown, iconRes = R.drawable.ic_question, color = AppColors.YellowDark)
}
