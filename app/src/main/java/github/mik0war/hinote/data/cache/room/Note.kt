package github.mik0war.hinote.data.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name="Header") val header: String,
    @ColumnInfo(name="Body") val body: String,
    @ColumnInfo(name="DateTime") val dateTime: String,
    @ColumnInfo(name="LastEditedDateTime") val lastEditedDateTime: String?,
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0

    fun update(newHeader: String,
               newBody: String,
               newDateTime: String
    ): Note =
        Note(newHeader, newBody, dateTime, newDateTime).also { it.id = this.id }
}
