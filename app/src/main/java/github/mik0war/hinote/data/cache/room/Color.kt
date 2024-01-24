package github.mik0war.hinote.data.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "colors")
class Color(
    @ColumnInfo(name = "buttonsColor") val buttonsColor: Int,
    @ColumnInfo(name = "backgroundColor") val backgroundColor: Int,
) {
    @PrimaryKey(autoGenerate = true) var id = 0
}