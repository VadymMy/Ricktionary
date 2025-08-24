package com.vadymmy.ricktionary.data.characters.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vadymmy.ricktionary.data.characters.local.model.CharacterPageRemoteKeysEntity

@Dao
interface CharactersRemoteKeysDao {
    @Query("SELECT * FROM ${CharacterPageRemoteKeysEntity.TABLE_NAME} WHERE page = :page")
    suspend fun getRemoteKeysByPage(page: Int): CharacterPageRemoteKeysEntity?

    @Query("SELECT MAX(page) FROM ${CharacterPageRemoteKeysEntity.TABLE_NAME}")
    suspend fun getLastRemoteKeysPage(): Int?

    @Query("SELECT MIN(page) FROM ${CharacterPageRemoteKeysEntity.TABLE_NAME}")
    suspend fun getFirstRemoteKeysPage(): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteKeys(remoteKeys: CharacterPageRemoteKeysEntity)

    @Query("DELETE FROM ${CharacterPageRemoteKeysEntity.TABLE_NAME}")
    suspend fun clearRemoteKeys()
}
