package fr.vannsup.tp.data.networking.api

import fr.vannsup.tp.data.model.Character
import fr.vannsup.tp.data.networking.api.CharacterApi.Companion
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {

    @GET(GET_ALL_CHARACTER_PATH)
    suspend fun getAllCharacter(
        @Query("page") page: Int
    ): Response<List<Character>>

    companion object {
        const val GET_ALL_CHARACTER_PATH = "character"
        const val GET_ALL_CHARACTER_DETAILS_PATH = "character{character_id}"
    }

}