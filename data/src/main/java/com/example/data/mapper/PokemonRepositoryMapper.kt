package com.example.data.mapper

import android.util.Log
import com.example.common.extentions.generateImageUrl
import com.example.data.remote.responses.PokemonListResponse
import com.example.data.remote.responses.PokemonResponse
import com.example.domain.entities.CriesEntity
import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonDataEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.entities.StatEntity
import com.example.domain.entities.TypeEntity
import com.google.gson.JsonParser
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.util.Locale

object PokemonRepositoryMapper {

    fun mapFromPokemonListResponse(type: PokemonListResponse): PokemonListEntity {
        val pokemonListEntities = type.results.mapIndexed { index, entity ->
            val number = if (entity.url.endsWith("/")) {
                entity.url.dropLast(1).takeLastWhile { it.isDigit() }
            } else {
                entity.url.takeLastWhile { it.isDigit() }
            }
            val url = generateImageUrl(number)
            PokemonDataEntity(
                pokemonName = entity.name,
                imageUrl = url,
                number = number.toInt()
            )
        }
        return PokemonListEntity(
                count = type.count,
                next = type.next ?: "",
                pokemonEntities = pokemonListEntities
            )
    }

    fun mapFromPokemonResponse(type: PokemonResponse): PokemonEntity {
        return type.let {
            PokemonEntity(
                id = it.id,
                name = it.name,
                order = it.order ,
                isDefault = it.is_default,
                locationAreaEncounters = it.location_area_encounters,
                weight = it.weight,
                height = it.height,
                stats = it.stats.map { st ->
                    StatEntity(
                        baseStat = st.base_stat,
                        effort = st.effort
                    )
                },
                cries = CriesEntity(
                    latest = it.cries.latest,
                    legacy = it.cries.legacy
                ),
                types = it.types.map { type ->
                    TypeEntity(
                        slot = type.slot
                    )
                },
            )
        }
    }


    fun parserErrorBody(response: ResponseBody?):String{
        return response?.let {
            val errorMessage = JsonParser().parse(it.string()).asJsonObject["message"].asString
            errorMessage.ifEmpty { "Whoops! Something went wrong. Please try again." }
            errorMessage
        }?:"Whoops! Unknown error occurred. Please try again"
    }
    fun message(throwable: Throwable?):String{
        when (throwable) {
            is SocketTimeoutException -> return "Whoops! Connection time out. Please try again"
            is IOException -> return "Whoops! No Internet Connection. Please try again"
            is HttpException -> return try {
                val errorJsonString = throwable.response()?.errorBody()?.string()
                val errorMessage = JsonParser().parse(errorJsonString).asJsonObject["message"].asString
                errorMessage.ifEmpty { "Whoops! Something went wrong. Please try again." }
            }catch (e:Exception){
                "Whoops! Unknown error occurred. Please try again now"
            }
        }
        return "Whoops! Unknown error occurred. Please try again"
    }
    private fun code(throwable: Throwable?):Int{
        return if (throwable is HttpException) (throwable).code()
        else  0
    }


}