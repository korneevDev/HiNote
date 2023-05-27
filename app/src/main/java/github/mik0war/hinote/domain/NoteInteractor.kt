package github.mik0war.hinote.domain

import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.entity.NoteModel
import javax.inject.Inject

interface NoteInteractor {
    suspend fun getNoteList(): List<NoteModel>
    suspend fun addNote(header: String, body: String)
    suspend fun undoDeletingNote()
    suspend fun updateNote(id: Int, header: String, body: String)
    suspend fun removeNote(id: Int)

    class Base @Inject constructor(
        private val repository: NoteRepository,
        private val exceptionHandler: ExceptionHandler,
        private val currentDateTime: CurrentDateTime
    ) : NoteInteractor {

        override suspend fun getNoteList(): List<NoteModel> = try{
            repository.getNotesList().map {
                it.mapToNoteModel()
            }
        } catch (e: Exception){
            listOf(exceptionHandler.handle(e))
        }

        override suspend fun undoDeletingNote() {
            repository.saveCachedNote()
        }

        override suspend fun addNote(header: String, body: String) =
            repository.saveNote(header, body, currentDateTime.getCurrentTime())

        override suspend fun updateNote(id: Int, header: String, body: String) {
            repository.updateNote(id, header, body, currentDateTime.getCurrentTime())
        }

        override suspend fun removeNote(id: Int) = repository.removeNote(id)
    }
}