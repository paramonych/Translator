package tech.dobrobot.apps.data.processing

import tech.dobrobot.apps.api.translation.GoogleTranslationApi
import tech.dobrobot.apps.utils.remote.RemoteDataSource
import javax.inject.Inject
import tech.dobrobot.apps.data.database.local.LocalDatabase
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import tech.dobrobot.apps.utils.Constants
import tech.dobrobot.apps.utils.extensions.emptyIfNull
import tech.dobrobot.apps.utils.extensions.shorten
import tech.dobrobot.apps.utils.remote.RemoteResult
import tech.dobrobot.apps.utils.remote.succeeded
import java.util.*

class TranslationPipeline @Inject constructor(
    private val api: GoogleTranslationApi,
    private val database: LocalDatabase
): RemoteDataSource() {

    suspend fun loadTranslation(originalText: String, text: String): RemoteResult<*> {

        when (val result = getResult { api.doTranslation(originalText, "ru", text.shorten()) }) {
            is RemoteResult.Success -> {
                if (result.succeeded) {
                    result.data.let {
                        val translationRecord = TranslationRecord(
                            (Math.random() * 1000000).toInt(),
                            Date().toString(),
                            it.text.emptyIfNull(),
                            it.result.ru
                        )

                        database.translationRecordDao().insert(translationRecord)

                        return RemoteResult.Success(it.result.ru)
                    }

                } else {
                    return RemoteResult.Error(Constants.GENERIC_ERROR)
                }
            }
            else -> return result as RemoteResult.Error
        }
    }
}