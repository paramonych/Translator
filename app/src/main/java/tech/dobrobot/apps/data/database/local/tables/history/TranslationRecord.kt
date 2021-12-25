package tech.dobrobot.apps.data.database.local.tables.history

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "translation_record")
data class TranslationRecord(
    @PrimaryKey val id: Int,
    val date: String,
    val original: String,
    val translated: String
)