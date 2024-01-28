package github.mik0war.hinote.core.data

import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.data.cache.room.NoteDAO
import github.mik0war.hinote.domain.NoNotesException

class TestNoteDAO : NoteDAO {
    private var list = ArrayList<Note>()

    override fun getAllOrderedByCreationTime(): List<Note> =
        list.ifEmpty { throw NoNotesException() }

    override fun getAllOrderedByLastEditedTime(): List<Note> =
        list.ifEmpty { throw NoNotesException() }

    override fun getNoteByID(id: Int) = list.first { it.id == id }

    override fun update(
        noteId: Int,
        newHeader: String,
        newText: String,
        newMainColor: Int,
        newButtonsColor: Int,
        lastEditedTime: Long
    ) {
        val index = list.indexOfFirst { it.id == noteId }
        list[index] = Note(
            newHeader,
            newText,
            list[index].dateTime,
            lastEditedTime,
            newMainColor,
            newButtonsColor
        ).also { it.id = noteId }
    }

    override fun createNote(note: Note) {
        list.add(note)
    }

    override fun delete(id: Int) {
        val index = list.indexOfFirst { it.id == id }
        list.removeAt(index)
    }
}