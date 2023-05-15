package github.mik0war.hinote.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Insert
    fun createNote(note: Note)

    @Delete
    fun delete(note: Note)

}