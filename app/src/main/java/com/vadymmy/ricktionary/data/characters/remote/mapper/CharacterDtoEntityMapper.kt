package com.vadymmy.ricktionary.data.characters.remote.mapper

import com.vadymmy.ricktionary.data.characters.local.model.CharacterEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterEpisodeEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterLocationEntity
import com.vadymmy.ricktionary.data.characters.local.model.CharacterOriginEntity
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterGenderDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterLocationDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterOriginDto
import com.vadymmy.ricktionary.data.characters.remote.model.CharacterStatusDto

fun CharacterDto.toEntityModel(): CharacterEntity = CharacterEntity(
    id = this.id,
    originId = parseIdFromUrl(this.origin.url),
    locationId = parseIdFromUrl(this.location.url),
    url = this.url,
    name = this.name,
    species = this.species,
    type = this.type,
    imageUrl = this.imageUrl,
    statusKey = this.status.toEntityKey(),
    genderKey = this.gender.toEntityKey()
)

fun CharacterDto.toEpisodeEntities(): List<CharacterEpisodeEntity> = this.episodes.mapNotNull { episodeUrl ->
    parseIdFromUrl(episodeUrl)?.let { episodeId ->
        CharacterEpisodeEntity(
            id = episodeId,
            url = episodeUrl
        )
    }
}

fun CharacterOriginDto.toEntityModel(): CharacterOriginEntity? = parseIdFromUrl(this.url)?.let { id ->
    CharacterOriginEntity(
        id = id,
        name = this.name,
        url = this.url
    )
}

fun CharacterLocationDto.toEntityModel(): CharacterLocationEntity? = parseIdFromUrl(this.url)?.let { id ->
    CharacterLocationEntity(
        id = id,
        name = this.name,
        url = this.url
    )
}

private fun CharacterStatusDto.toEntityKey(): Int = this.toDomainModel().key

private fun CharacterGenderDto.toEntityKey(): Int = this.toDomainModel().key

private fun parseIdFromUrl(url: String): Int? {
    return url.substringAfterLast("/").toIntOrNull()
}
