package github.mik0war.hinote.data.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import github.mik0war.hinote.core.MapperTime

@Entity(tableName = "note_creation_time")
data class Time(
    @ColumnInfo(name = "createdTime") val createdTime: Long,
    @ColumnInfo(name = "note_id") val noteId: Int,
    @ColumnInfo(name = "lastEditedDateTime") val lastEditedDateTime: Long? = null
) {
    @PrimaryKey(autoGenerate = true) var id = 0
    fun <T> map(timeMapper: MapperTime<T>) = timeMapper.map(createdTime, lastEditedDateTime)
}