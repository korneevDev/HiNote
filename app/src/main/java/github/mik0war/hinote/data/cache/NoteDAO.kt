package github.mik0war.hinote.data.cache

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDAO {
    @Query("SELECT * FROM note ORDER BY dateTime")
    fun getAll(): List<Note>

    @Query("SELECT * FROM note WHERE id=:id")
    fun getNoteByID(id: Int) : Note

    @Update
    fun update(note: Note)

    @Insert
    fun createNote(note: Note)

    @Delete
    fun delete(note: Note)

}