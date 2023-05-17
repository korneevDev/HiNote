package github.mik0war.hinote.domain

import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.model.NoteModel

interface NoteInteractor {
    suspend fun getNoteList(): List<NoteModel>
    suspend fun addNote(header: String, body: String)
    suspend fun addNote(header: String, body: String, dateTime: String)
    suspend fun updateNote(id: Int, header: String, body: String)
    suspend fun removeNote(id: Int): NoteModel

    class Base(
        private val repository: NoteRepository,
        private val exceptionHandler: ExceptionHandler,
        private val currentDateTime: CurrentDateTime
    ) : NoteInteractor {

        override suspend fun getNoteList(): List<NoteModel> = try{
            repository.getNotesList().map {
                it.mapTo()
            }
        } catch (e: Exception){
            listOf(exceptionHandler.handle(e))
        }

        override suspend fun addNote(header: String, body: String, dateTime: String) {
            repository.saveNote(header, body, dateTime)
        }

        override suspend fun addNote(header: String, body: String) =
            addNote(header, body, currentDateTime.getCurrentTime())

        override suspend fun updateNote(id: Int, header: String, body: String) {
            repository.updateNote(id, header, body)
        }

        override suspend fun removeNote(id: Int): NoteModel =
            repository.removeNote(id).mapTo()
    }
}