package github.mik0war.hinote.data.cache

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.model.NoteDataModel
import github.mik0war.hinote.domain.NoNotesException

interface CacheDataSource {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun save(header: String, body: String, dateTime: String)
    suspend fun save(note: NoteDataModel)
    suspend fun update(id: Int, newHeader: String, newBody: String)
    suspend fun remove(id: Int): NoteDataModel

    class Base(
        private val mapper: MapperParametrised<NoteDataModel>,
        private val noteDAO: NoteDAO
    ): CacheDataSource {
        override suspend fun getNotesList(): List<NoteDataModel> {
            val list = noteDAO.getAll().map {
                mapper.map(it.id, it.header, it.body, it.dateTime)
            }
            return list.ifEmpty { throw NoNotesException() }
        }


        override suspend fun save(header: String, body: String, dateTime: String) {
            noteDAO.createNote(Note(header, body, dateTime))
        }

        override suspend fun save(note: NoteDataModel) {
            noteDAO.createNote(note.mapToNote())
        }

        override suspend fun update(id: Int, newHeader: String, newBody: String) {
            val note = noteDAO.getNoteByID(id)
            val newNote = note.update(newHeader, newBody)
            noteDAO.update(newNote)
        }

        override suspend fun remove(id: Int): NoteDataModel {
            val note = noteDAO.getNoteByID(id)
            noteDAO.delete(note)

            return mapper.map(note.id, note.header, note.body, note.dateTime)
        }
    }
}