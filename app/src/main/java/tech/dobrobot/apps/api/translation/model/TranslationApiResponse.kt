package tech.dobrobot.apps.api.translation.model

import com.google.gson.annotations.SerializedName

data class TranslationApiResponse(
    val code: Int,
    val status: String?,
    val message: String?,
    val text: String?,
    val from: String?,
    val to: String?,
    @SerializedName("translate_capital") val price: Boolean?,
    val protected: Array<String>?,
    val result: String?,
)

//{
//    "code":200
//    "status":"success"
//    "message":""
//    "text":"Hello world, IM a Developer what is YOU name ?"
//    "from":"auto"
//    "to":"ru"
//    "translate_capital":true
//    "protected":[]
//    "result":{
//        "ru":"Привет, мир! Я разработчик, как ВАС зовут?"
//    }
//    "count_characters":46
//    "count_words":9
//}