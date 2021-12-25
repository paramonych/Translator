package tech.dobrobot.apps.api.translation

import retrofit2.Response
import retrofit2.http.*
import tech.dobrobot.apps.api.translation.model.TranslationApiResponse
import tech.dobrobot.apps.utils.Constants

interface GoogleTranslationApi {
    @FormUrlEncoded
    @Headers("x-rapidapi-host: ${Constants.API_HOST}")
    @POST("/api_translate_unlimited.php")
    suspend fun doTranslation(
        @Field("text") original: String,
        @Field("to") to: String,
        @Header("x-rapidapi-key") text: String
    ): Response<TranslationApiResponse>
}