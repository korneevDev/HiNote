package github.mik0war.hinote.data.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {
    @Query(
        "SELECT * " +
                "FROM note " +
                "ORDER BY dateTime"
    )
    fun getAllOrderedByCreationTime(): List<Note>

    @Query(
        "SELECT * " +
                "FROM note " +
                "ORDER BY " +
                "CASE " +
                "WHEN lastEditedDateTime NOT NULL " +
                "THEN lastEditedDateTime " +
                "ELSE dateTime " +
                "END DESC"
    )
    fun getAllOrderedByLastEditedTime(): List<Note>

    @Query(
        "SELECT * " +
                "FROM note " +
                "WHERE id=:id"
    )
    fun getNoteByID(id: Int): Note

    @Query("UPDATE note " +
            "SET " +
                "Header = :newHeader, " +
                "Body = :newText, " +
                "LastEditedDateTime = :lastEditedTime, " +
                "MainColor = :newMainColor, " +
                "ButtonsColor = :newButtonsColor " +
            "WHERE id = :noteId and (" +
                    "Body <> :newText or " +
                    "Header <> :newHeader or " +
                    "MainColor <> :newMainColor or " +
                    "ButtonsColor <> :newButtonsColor)")
    fun update(
        noteId: Int,
        newHeader: String,
        newText: String,
        newMainColor: Int,
        newButtonsColor: Int,
        lastEditedTime: Long
    )

    @Insert
    fun createNote(note: Note)

    @Query("DELETE FROM note WHERE id =:id")
    fun delete(id: Int)

}