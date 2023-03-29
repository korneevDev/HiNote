package github.mik0war.hinote.domain

import github.mik0war.hinote.data.NoteDAO

interface NoteRepository {
    suspend fun getNotesList() : List<NoteDAO>
    suspend fun saveNote(note: NoteDAO)
    suspend fun removeNote(id: Int)
}