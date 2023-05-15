package github.mik0war.hinote.data.cache

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.model.NoteDataModel

interface CacheDataSource {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun save(header: String, body: String, dateTime: String)
    suspend fun remove(id: Int)

    class Base(
        private val mapper: MapperParametrised<NoteDataModel>,
        private val noteDAO: NoteDAO
    ): CacheDataSource {
        override suspend fun getNotesList(): List<NoteDataModel> =
            noteDAO.getAll().map{mapper.map(it.id, it.header, it.body, it.dateTime)}


        override suspend fun save(header: String, body: String, dateTime: String) {
            noteDAO.createNote(Note(header, body, dateTime))
        }

        override suspend fun remove(id: Int) {
            TODO("Not yet implemented")
        }
    }
}