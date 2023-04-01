package github.mik0war.hinote.domain

import github.mik0war.hinote.data.NoteDataModel

interface NoteRepository {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun saveNote(note: NoteDataModel)
    suspend fun removeNote(id: Int)
}