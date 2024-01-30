package github.mik0war.hinote.data.cache.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDAO {
    @Query(
        "SELECT * " +
                "FROM note JOIN note_creation_time " +
                "ON note.id = note_creation_time.note_id " +
                "ORDER BY " +
                "CASE " +
                "WHEN lastEditedDateTime NOT NULL " +
                "THEN lastEditedDateTime " +
                "ELSE createdTime " +
                "END DESC"
    )
    fun getAllOrderedByLastEditedTime(): Map<Note, Time>

    @Query(
        "SELECT * " +
                "FROM note JOIN note_creation_time " +
                "ON note.id = note_creation_time.note_id " +
                "WHERE note.id=:id"
    )
    fun getNoteByID(id: Int): Map<Note, Time>

    @Query("UPDATE note " +
            "SET " +
                "Header = :newHeader, " +
                "Body = :newText, " +
                "MainColor = :newMainColor, " +
                "ButtonsColor = :newButtonsColor " +
            "WHERE id = :noteId and (" +
                    "Body <> :newText or " +
                    "Header <> :newHeader or " +
                    "MainColor <> :newMainColor or " +
                    "ButtonsColor <> :newButtonsColor); ")
    fun updateNote(
        noteId: Int,
        newHeader: String,
        newText: String,
        newMainColor: Int,
        newButtonsColor: Int
    )

    @Query("UPDATE note_creation_time " +
            "SET " +
            "LastEditedDateTime = :lastEditedTime " +
            "WHERE note_id = :noteId")
    fun updateTime(
        noteId: Int,
        lastEditedTime: Long
    )

    @Insert
    fun createNote(note: Note): Long

    @Insert
    fun createTime(time: Time): Long

    @Query("DELETE FROM note WHERE id =:id")
    fun delete(id: Int)

}