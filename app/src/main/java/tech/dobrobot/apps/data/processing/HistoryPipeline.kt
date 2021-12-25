package tech.dobrobot.apps.data.processing

import androidx.lifecycle.LiveData
import tech.dobrobot.apps.data.database.local.LocalDatabase
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import javax.inject.Inject

class HistoryPipeline @Inject constructor(private val database: LocalDatabase) {
    val historyRecords: LiveData<List<TranslationRecord>> = database.translationRecordDao().historyList()

    suspend fun removeHistoryRecord(record: TranslationRecord) {
        database.translationRecordDao().deleteRecord(record)
    }
}