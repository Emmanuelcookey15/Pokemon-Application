package com.example.data.utils

import com.example.data.mapper.PokemonRepositoryMapper.message
import com.example.data.mapper.PokemonRepositoryMapper.parserErrorBody
import com.example.domain.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class NetworkBoundResource @Inject constructor(){

    private  val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun <RequestType, ResultType> triggerCall(apiCall: Response<RequestType>, mapper: (RequestType) -> ResultType): Flow<Resource<ResultType>> {
        return withContext(ioDispatcher) {
            flow {
                val response: Response<RequestType> = apiCall
                if (response.isSuccessful){
                    response.body()?.let {
                        emit(Resource.Success(data = mapper(it)))
                    }?: emit(Resource.Error(message = "Unknown error occurred"))
                }else{
                    emit(Resource.Error(message = parserErrorBody(response.errorBody())))
                }

            }.catch { error ->
                emit(Resource.Error(message = message(error)))
            }
        }
    }

}