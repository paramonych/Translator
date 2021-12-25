package tech.dobrobot.apps.data.database.local.tables.history

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery

@Dao
interface TranslationRecordDao {
    @Query("SELECT * FROM translation_record")
    fun historyList(): LiveData<List<TranslationRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: TranslationRecord)

    @RawQuery()
    suspend fun delete(query: SimpleSQLiteQuery): Any
}