package github.mik0war.hinote.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @ColumnInfo(name="Header") val header: String,
    @ColumnInfo(name="Body") val body: String,
    @ColumnInfo(name="DateTime") val dateTime: String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}
