package com.example.data.mapper

import com.example.data.remote.responses.PokemonListResponse
import com.example.data.remote.responses.PokemonResponse
import com.example.domain.entities.CriesEntity
import com.example.domain.entities.PokemonEntity
import com.example.domain.entities.PokemonListEntity
import com.example.domain.entities.StatEntity
import com.example.domain.entities.TypeEntity
import javax.inject.Inject

object PokemonRepositoryMapper {

    fun mapFromPokemonListResponse(type: PokemonListResponse): List<PokemonListEntity> {
        return type.results.map {
            PokemonListEntity(
                it.name,
                it.url
            )
        }
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


}