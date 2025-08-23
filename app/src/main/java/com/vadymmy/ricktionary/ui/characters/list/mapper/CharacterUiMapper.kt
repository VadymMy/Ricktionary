package com.vadymmy.ricktionary.ui.characters.list.mapper

import com.vadymmy.ricktionary.domain.characters.model.Character
import com.vadymmy.ricktionary.domain.characters.model.CharacterGender
import com.vadymmy.ricktionary.domain.characters.model.CharacterStatus
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterGenderUiModel
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterStatusUiModel
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel

fun List<Character>.toUiModels() : List<CharacterItemUiModel> = this.map {
    CharacterItemUiModel(
        id = it.id,
        name = it.name,
        imageUrl = it.imageUrl,
        location = it.location.name,
        status = it.status.toUiModel(),
        gender = it.gender.toUiModel()
    )
}

fun CharacterStatus.toUiModel() : CharacterStatusUiModel = when(this) {
    CharacterStatus.Alive -> CharacterStatusUiModel.Alive
    CharacterStatus.Dead -> CharacterStatusUiModel.Dead
    CharacterStatus.Unknown -> CharacterStatusUiModel.Unknown
}

fun CharacterGender.toUiModel() : CharacterGenderUiModel = when(this) {
    CharacterGender.Male -> CharacterGenderUiModel.Male
    CharacterGender.Female -> CharacterGenderUiModel.Female
    CharacterGender.Genderless -> CharacterGenderUiModel.Genderless
    CharacterGender.Unknown -> CharacterGenderUiModel.Unknown
}
