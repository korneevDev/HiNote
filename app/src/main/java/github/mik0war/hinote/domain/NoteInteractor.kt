package github.mik0war.hinote.domain

import github.mik0war.hinote.core.Mapper
import github.mik0war.hinote.data.NoteRepository

interface NoteInteractor {
    suspend fun getNoteList(): List<NoteModel>
    suspend fun addNote(id: Int, header: String, body: String, dateTime: String)
    suspend fun removeNote(id: Int)

    class Base(
        private val repository: NoteRepository,
        private val exceptionHandler: ExceptionHandler,
        private val mapperToNote: Mapper.ToNote,
        private val mapperToDataModel: Mapper.ToDataModel
    ) : NoteInteractor {

        override suspend fun getNoteList(): List<NoteModel> = try{
            repository.getNotesList().map {
                it.map(mapperToNote)
            }
        } catch (e: Exception){
            listOf(exceptionHandler.handle(e))
        }

        override suspend fun addNote(id: Int, header: String, body: String, dateTime: String) =
            repository.saveNote(mapperToDataModel.map(id, header, body, dateTime))

        override suspend fun removeNote(id: Int) {
            repository.removeNote(id)
        }
    }
}