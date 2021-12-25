package tech.dobrobot.apps.api.translation

import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*
import tech.dobrobot.apps.api.translation.model.TranslationApiResponse
import tech.dobrobot.apps.utils.Constants

interface GoogleTranslationApi {
    @FormUrlEncoded
    @Headers("x-rapidapi-host: ${Constants.API_HOST}")
    @POST("/api_translate_unlimited.php")
    suspend fun doTranslation(
        @Header("x-rapidapi-key") key: String,
        @Field("text") text: String,
        @Field("from") from: String = "auto",
        @Field("to") to: String = "ru",
        @Field("translate_capital") capital: String = "true",
    ): Response<TranslationApiResponse>
}