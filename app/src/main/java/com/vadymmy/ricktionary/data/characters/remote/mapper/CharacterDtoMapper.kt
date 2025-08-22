package com.vadymmy.ricktionary.data.characters.remote.mapper

import com.vadymmy.ricktionary.data.characters.remote.model.CharacterDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterLocationDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterOriginDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterStatusDto
import com.vadymmy.ricktionary.domain.characters.model.Character
import com.vadymmy.ricktionary.domain.characters.model.CharacterLocation
import com.vadymmy.ricktionary.domain.characters.model.CharacterOrigin
import com.vadymmy.ricktionary.domain.characters.model.CharacterStatus

fun List<CharacterDto>.toDomainModels(): List<Character> = this.map { it.toDomainModel() }

fun CharacterDto.toDomainModel(): Character = Character(
    id = this.id,
    name = this.name,
    url = this.url,
    status = this.status.toDomainModel(),
    species = this.species,
    type = this.type,
    gender = this.gender,
    origin = this.origin.toDomainModel(),
    location = this.location.toDomainModel(),
    imageUrl = this.imageUrl,
    episodes = this.episodes
)

private fun CharacterStatusDto.toDomainModel(): CharacterStatus = when (this) {
    CharacterStatusDto.Alive -> CharacterStatus.Alive
    CharacterStatusDto.Dead -> CharacterStatus.Dead
    CharacterStatusDto.Unknown -> CharacterStatus.Unknown
}

private fun CharacterOriginDto.toDomainModel(): CharacterOrigin = CharacterOrigin(name = this.name, url = this.url)

private fun CharacterLocationDto.toDomainModel(): CharacterLocation = CharacterLocation(name = this.name, url = this.url)
