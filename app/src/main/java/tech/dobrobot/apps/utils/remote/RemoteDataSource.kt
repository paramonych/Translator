package tech.dobrobot.apps.utils.remote

import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Response
import tech.dobrobot.apps.utils.Constants

abstract class RemoteDataSource {
    suspend fun <T> getResult(call: suspend () -> Response<T>): RemoteResult<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return RemoteResult.Success(body)
            } else if (response.errorBody() != null) {
                val errorBody = getErrorBody(response.errorBody())
                return error(errorBody?.message ?: Constants.GENERIC_ERROR)
            }

            return error(Constants.GENERIC_ERROR)
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun error(message: String): RemoteResult.Error = RemoteResult.Error(message)

    private fun getErrorBody(responseBody: ResponseBody?): GenericResponse? =
        try {
            Gson().fromJson(responseBody?.charStream(), GenericResponse::class.java)
        } catch (e: Exception) {
            null
        }
}

