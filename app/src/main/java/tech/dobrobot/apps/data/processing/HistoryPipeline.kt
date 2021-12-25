package tech.dobrobot.apps.data.processing

import androidx.lifecycle.LiveData
import tech.dobrobot.apps.data.database.local.LocalDatabase
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import javax.inject.Inject
import androidx.sqlite.db.SimpleSQLiteQuery
import tech.dobrobot.apps.utils.Constants

class HistoryPipeline @Inject constructor(private val database: LocalDatabase) {
    val historyRecords: LiveData<List<TranslationRecord>> = database.translationRecordDao().historyList()

    suspend fun removeHistoryRecord(id: Int) {
        database.translationRecordDao().delete(
            SimpleSQLiteQuery(
                "SELECT * FROM ${Constants.RECORDS_TABLE_NAME} WHERE id = ?",
                arrayOf(id)
            )
        )
    }
}