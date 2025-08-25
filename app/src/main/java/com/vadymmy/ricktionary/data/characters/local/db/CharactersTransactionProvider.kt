package com.vadymmy.ricktionary.data.characters.local.db

import androidx.room.withTransaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharactersTransactionProvider @Inject constructor(
    private val database: CharactersDatabase
) {
    suspend fun <T> runAsTransaction(block: suspend () -> T): T {
        return database.withTransaction { block() }
    }
}
