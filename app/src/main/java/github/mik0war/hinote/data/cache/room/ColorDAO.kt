package github.mik0war.hinote.data.cache.room

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ColorDAO {
    @Query("SELECT * FROM colors WHERE id =:id")
    fun getColor(id: Int): Color

    @Query("SELECT * FROM colors")
    fun getColors(): List<Color>
}