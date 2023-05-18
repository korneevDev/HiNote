package github.mik0war.hinote.core.data

import github.mik0war.hinote.data.cache.Note
import github.mik0war.hinote.data.cache.NoteDAO

class TestNoteDAO : NoteDAO {
    private var list = ArrayList<Note>()

    override fun getAll(): List<Note> = list

    override fun getNoteByID(id: Int) = list.first{it.id == id}

    override fun update(note: Note){
        val index = list.indexOfFirst { it.id == note.id }
        list[index] = list[index].update(note.header, note.body)
    }

    override fun createNote(note: Note) {
        list.add(note)
    }

    override fun delete(note: Note) {
        list.remove(note)
    }
}