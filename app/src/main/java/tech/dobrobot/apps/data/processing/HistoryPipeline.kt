package tech.dobrobot.apps.data.processing

import androidx.lifecycle.LiveData
import tech.dobrobot.apps.data.database.local.LocalDatabase
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import javax.inject.Inject
import androidx.sqlite.db.SimpleSQLiteQuery

class HistoryPipeline @Inject constructor(private val database: LocalDatabase) {
    val historyRecords: LiveData<List<TranslationRecord>> = database.translationRecordDao().historyList()

    suspend fun removeHistoryRecord(id: Int) {
        database.translationRecordDao().delete(
            SimpleSQLiteQuery(
                "SELECT * FROM song WHERE id = ?",
                arrayOf(id)
            )
        )
    }
}