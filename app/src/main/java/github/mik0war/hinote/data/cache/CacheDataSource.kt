package github.mik0war.hinote.data.cache

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.model.NoteDataModel

interface CacheDataSource {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun save(header: String, body: String, dateTime: String)
    suspend fun update(id: Int, newHeader: String, newBody: String)
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

        override suspend fun update(id: Int, newHeader: String, newBody: String) {
            val note = noteDAO.getNoteByID(id)
            val newNote = note.update(newHeader, newBody)
            noteDAO.update(newNote)
        }

        override suspend fun remove(id: Int) {
            val note = noteDAO.getNoteByID(id)
            noteDAO.delete(note)
        }
    }
}