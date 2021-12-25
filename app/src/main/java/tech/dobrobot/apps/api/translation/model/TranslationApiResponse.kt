package tech.dobrobot.apps.api.translation.model

import com.google.gson.annotations.SerializedName

data class TranslationApiResponse(
    val code: Int,
    val status: String?,
    val message: String?,
    val text: String?,
    val from: String?,
    val to: String?,
    @SerializedName("translate_capital") val cap: Boolean?,
    val protected: Any,
    val result: TranslationText,
    @SerializedName("count_characters") val chars: Int,
    @SerializedName("count_words") val words: Int,
)