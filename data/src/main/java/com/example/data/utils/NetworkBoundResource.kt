package com.example.data.utils

import com.example.data.mapper.PokemonRepositoryMapper.message
import com.example.data.mapper.PokemonRepositoryMapper.parserErrorBody
import com.example.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

class NetworkBoundResource @Inject constructor(){

    private  val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun<ResultType> downloadData(api : suspend () -> Response<ResultType>): Resource<ResultType> {
        return withContext(ioDispatcher) {
            try {
                val response: Response<ResultType> = api()
                if (response.isSuccessful){
                    response.body()?.let {
                        Resource.Success(data = it)
                    }?: Resource.Error(message = "Unknown error occurred")
                }else{
                    Resource.Error(message = parserErrorBody(response.errorBody()))
                }

            } catch(error: Exception) {
                Timber.e(error.localizedMessage)
                Resource.Error(message = message(error))
            }
        }
    }

}