package tech.dobrobot.apps.data.database.local.tables.history

import androidx.lifecycle.LiveData
import androidx.room.*
import tech.dobrobot.apps.utils.Constants

@Dao
interface TranslationRecordDao {
    @Query("SELECT * FROM ${Constants.RECORDS_TABLE_NAME}")
    fun historyList(): LiveData<List<TranslationRecord>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: TranslationRecord)

    @Delete
    suspend fun deleteRecord(record: TranslationRecord)
}