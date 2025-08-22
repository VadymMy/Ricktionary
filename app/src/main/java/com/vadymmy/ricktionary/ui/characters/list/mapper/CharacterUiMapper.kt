package com.vadymmy.ricktionary.ui.characters.list.mapper

import com.vadymmy.ricktionary.domain.characters.model.Character
import com.vadymmy.ricktionary.domain.characters.model.CharacterStatus
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterStatusUiModel
import com.vadymmy.ricktionary.ui.characters.list.model.CharacterItemUiModel

fun List<Character>.toUiModels() : List<CharacterItemUiModel> = this.map {
    CharacterItemUiModel(
        id = it.id,
        name = it.name,
        imageUrl = it.imageUrl,
        location = it.location.name,
        status = it.status.toUiModel()
    )
}

fun CharacterStatus.toUiModel() : CharacterStatusUiModel = when(this) {
    CharacterStatus.Alive -> CharacterStatusUiModel.Alive
    CharacterStatus.Dead -> CharacterStatusUiModel.Dead
    CharacterStatus.Unknown -> CharacterStatusUiModel.Unknown
}
