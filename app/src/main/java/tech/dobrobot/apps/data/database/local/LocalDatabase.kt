package tech.dobrobot.apps.data.database.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecord
import tech.dobrobot.apps.data.database.local.tables.history.TranslationRecordDao
import tech.dobrobot.apps.utils.Constants.DATABASE_NAME
import tech.dobrobot.apps.utils.Constants.DATABASE_VERSION

@Database(entities = [TranslationRecord::class], version = DATABASE_VERSION, exportSchema = false)
abstract class LocalDatabase: RoomDatabase() {
    abstract fun translationRecordDao(): TranslationRecordDao

    companion object {
        fun buildDatabase(context: Context): LocalDatabase {
            return Room.databaseBuilder(context, LocalDatabase::class.java, DATABASE_NAME).build()
        }
    }
}