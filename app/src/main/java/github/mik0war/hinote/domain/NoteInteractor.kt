package github.mik0war.hinote.domain

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.domain.entity.NoteModel
import javax.inject.Inject

interface NoteInteractor {
    suspend fun getNoteList(): List<NoteModel>
    suspend fun addNote(header: String, body: String, mainColor: Int, buttonsColor: Int)
    suspend fun undoDeletingNote()
    suspend fun updateNote(id: Int, header: String, body: String, mainColor: Int, buttonsColor: Int)
    suspend fun removeNote(id: Int)

    class Base @Inject constructor(
        private val repository: NoteRepository,
        private val exceptionHandler: ExceptionHandler,
        private val currentDateTime: CurrentDateTime,
        private val mapperToNoteModel: MapperParametrised<NoteModel>
    ) : NoteInteractor {

        override suspend fun getNoteList(): List<NoteModel> = try {
            repository.getNotesList().map {
                it.map(mapperToNoteModel)
            }
        } catch (e: Exception) {
            listOf(exceptionHandler.handle(e))
        }

        override suspend fun undoDeletingNote() {
            repository.saveCachedNote()
        }

        override suspend fun addNote(
            header: String,
            body: String,
            mainColor: Int,
            buttonsColor: Int
        ) =
            repository.saveNote(
                header,
                body,
                currentDateTime.getCurrentTime(),
                mainColor,
                buttonsColor
            )

        override suspend fun updateNote(
            id: Int,
            header: String,
            body: String,
            mainColor: Int,
            buttonsColor: Int
        ) {
            repository.updateNote(
                id,
                header,
                body,
                currentDateTime.getCurrentTime(),
                mainColor,
                buttonsColor
            )
        }

        override suspend fun removeNote(id: Int) = repository.removeNote(id)
    }
}