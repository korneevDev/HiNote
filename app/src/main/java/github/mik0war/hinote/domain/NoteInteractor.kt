package github.mik0war.hinote.domain

import github.mik0war.hinote.core.Mapper
import java.util.*

interface NoteInteractor {
    suspend fun getNoteList(): List<NoteModel>
    suspend fun addNote(id: Int, header: String, body: String, date: Date)
    suspend fun removeNote(id: Int)

    class Base(
        private val repository: NoteRepository,
        private val errorHandler: ErrorHandler,
        private val mapperToNote: Mapper.ToNote,
        private val mapperToDataModel: Mapper.ToDataModel
    ) : NoteInteractor {

        override suspend fun getNoteList(): List<NoteModel> = try{
            repository.getNotesList().map {
                it.map(mapperToNote)
            }
        } catch (e: Exception){
            listOf(errorHandler.handle(e))
        }

        override suspend fun addNote(id: Int, header: String, body: String, date: Date) =
            repository.saveNote(mapperToDataModel.map(id, header, body, date))

        override suspend fun removeNote(id: Int) {
            repository.removeNote(id)
        }
    }
}