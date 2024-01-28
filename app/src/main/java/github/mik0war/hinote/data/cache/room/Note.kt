package github.mik0war.hinote.data.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name = "Header") val header: String,
    @ColumnInfo(name = "Body") val body: String,
    @ColumnInfo(name = "DateTime") val dateTime: Long,
    @ColumnInfo(name = "LastEditedDateTime") val lastEditedDateTime: Long?,
    @ColumnInfo(name = "MainColor") val mainColor: Int,
    @ColumnInfo(name = "ButtonsColor") val buttonsColor: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
