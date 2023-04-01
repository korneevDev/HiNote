package github.mik0war.hinote.domain

import github.mik0war.hinote.core.Mapper
import github.mik0war.hinote.data.NoteDataModel
import java.util.*

interface NoteInteractor {
    suspend fun getNoteList() : List<Note>
    suspend fun addNote(id: Int, header: String, body: String, date: Date)
    suspend fun removeNote(id: Int)

    abstract class Abstract : NoteInteractor{
        protected suspend fun <T> handleError(block: suspend () -> T) =
            try {
                block.invoke()
            } catch (e: Exception){
                throw e
            }
    }

    class Base(
        private val repository: NoteRepository,
        private val mapperToNote: Mapper<Note>,
        private val mapperToDAO: Mapper<NoteDataModel>
    ) : Abstract() {

        override suspend fun getNoteList() =
            handleError {
                return@handleError repository.getNotesList().map {
                    it.map(mapperToNote)
                }
            }

        override suspend fun addNote(id: Int, header: String, body: String, date: Date) =
            handleError { repository.saveNote(mapperToDAO.map(id, header, body, date)) }

        override suspend fun removeNote(id: Int) {
            handleError { repository.removeNote(id) }
        }
    }
}