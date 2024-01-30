package github.mik0war.hinote.data.cache.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.core.TimeModel

@Entity
data class Note(
    @ColumnInfo(name = "Header") val header: String,
    @ColumnInfo(name = "Body") val body: String,
    @ColumnInfo(name = "MainColor") val mainColor: Int,
    @ColumnInfo(name = "ButtonsColor") val buttonsColor: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    fun <T> map(mapper: MapperParametrised<T>, time: TimeModel) =
        mapper.map(id, header, body, time, mainColor, buttonsColor)
}
