package com.vadymmy.ricktionary.data.characters.local.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterWithRelationsEntity
import com.vadymmy.ricktionary.domain.characters.model.Character
import com.vadymmy.ricktionary.domain.characters.model.CharacterGender
import com.vadymmy.ricktionary.domain.characters.model.CharacterLocation
import com.vadymmy.ricktionary.domain.characters.model.CharacterOrigin
import com.vadymmy.ricktionary.domain.characters.model.CharacterStatus

fun PagingData<CharacterWithRelationsEntity>.toDomainModels() : PagingData<Character> = this.map {
    it.toDomainModel()
}

fun CharacterWithRelationsEntity.toDomainModel(): Character = Character(
    id = this.character.id,
    name = this.character.name,
    url = this.character.url,
    imageUrl = this.character.imageUrl,
    status = CharacterStatus.fromKey(this.character.statusKey),
    gender = CharacterGender.fromKey(this.character.genderKey),
    type = this.character.type,
    species = this.character.species,
    origin = this.origin?.toDomainModel(),
    location = this.location?.toDomainModel(),
    episodes = this.episodes.toEpisodeDomainModels()
)

private fun CharacterOriginEntity.toDomainModel(): CharacterOrigin = CharacterOrigin(
    name = this.name,
    url = this.url
)

private fun CharacterLocationEntity.toDomainModel(): CharacterLocation = CharacterLocation(
    name = this.name,
    url = this.url
)

private fun List<CharacterEpisodeEntity>.toEpisodeDomainModels() = this.map { it.url }
