package github.mik0war.hinote.core.data

import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.data.cache.room.NoteDAO
import github.mik0war.hinote.domain.NoNotesException

class TestNoteDAO : NoteDAO {
    private var list = ArrayList<Note>()

    override fun getAllOrderedByCreationTime(): List<Note> = list.ifEmpty { throw NoNotesException() }
    override fun getAllOrderedByLastEditedTime(): List<Note> = list.ifEmpty { throw NoNotesException() }

    override fun getNoteByID(id: Int) = list.first{it.id == id}

    override fun update(note: Note){
        val index = list.indexOfFirst { it.id == note.id }
        list[index] = list[index].update(note.header, note.body, note.lastEditedDateTime ?: 0)
    }

    override fun createNote(note: Note) {
        list.add(note)
    }

    override fun delete(note: Note) {
        list.remove(note)
    }
}