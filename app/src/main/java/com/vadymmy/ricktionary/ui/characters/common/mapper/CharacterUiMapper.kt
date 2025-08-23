package com.vadymmy.ricktionary.ui.characters.common.mapper

import com.vadymmy.ricktionary.domain.characters.model.Character
import com.vadymmy.ricktionary.domain.characters.model.CharacterGender
import com.vadymmy.ricktionary.domain.characters.model.CharacterStatus
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterGenderUiModel
import com.vadymmy.ricktionary.ui.characters.common.model.CharacterStatusUiModel
import com.vadymmy.ricktionary.ui.characters.details.model.CharacterUiModel
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel

fun List<Character>.toItemUiModels(): List<CharacterItemUiModel> = this.map { it.toItemUiModel() }

fun Character.toUiModel() = CharacterUiModel(
    id = this.id,
    name = this.name,
    type = this.type,
    species = this.species,
    imageUrl = this.imageUrl,
    origin = this.origin.name,
    location = this.location.name,
    episodesNumber = this.episodes.size,
    status = this.status.toUiModel(),
    gender = this.gender.toUiModel()
)

fun Character.toItemUiModel() = CharacterItemUiModel(
    id = this.id,
    name = this.name,
    imageUrl = this.imageUrl,
    location = this.location.name,
    status = this.status.toUiModel(),
    gender = this.gender.toUiModel()
)

fun CharacterStatus.toUiModel(): CharacterStatusUiModel = when (this) {
    CharacterStatus.Alive -> CharacterStatusUiModel.Alive
    CharacterStatus.Dead -> CharacterStatusUiModel.Dead
    CharacterStatus.Unknown -> CharacterStatusUiModel.Unknown
}

fun CharacterGender.toUiModel(): CharacterGenderUiModel = when (this) {
    CharacterGender.Male -> CharacterGenderUiModel.Male
    CharacterGender.Female -> CharacterGenderUiModel.Female
    CharacterGender.Genderless -> CharacterGenderUiModel.Genderless
    CharacterGender.Unknown -> CharacterGenderUiModel.Unknown
}
