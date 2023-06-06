package github.mik0war.hinote.data.cache

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.data.cache.room.NoteDAO
import github.mik0war.hinote.data.entity.NoteDataModel
import github.mik0war.hinote.domain.NoNotesException
import javax.inject.Inject

interface CacheDataSource {
    suspend fun getNotesList() : List<NoteDataModel>
    suspend fun save(header: String, body: String, dateTime: Long)
    suspend fun save(note: NoteDataModel)
    suspend fun update(id: Int, newHeader: String, newBody: String, newDateTime: Long)
    suspend fun remove(id: Int): NoteDataModel

    class Base @Inject constructor(
        private val mapper: MapperParametrised<NoteDataModel>,
        private val noteDAO: NoteDAO
    ): CacheDataSource {
        override suspend fun getNotesList(): List<NoteDataModel> {
            val list = noteDAO.getAllOrderedByLastEditedTime().map {
                mapper.map(it.id, it.header, it.body, it.dateTime, it.lastEditedDateTime)
            }
            return list.ifEmpty { throw NoNotesException() }
        }


        override suspend fun save(header: String, body: String, dateTime: Long) {
            noteDAO.createNote(Note(header, body, dateTime, null))
        }

        override suspend fun save(note: NoteDataModel) {
            noteDAO.createNote(note.mapToNote())
        }

        override suspend fun update(id: Int, newHeader: String, newBody: String, newDateTime: Long) {
            val note = noteDAO.getNoteByID(id)
            if(note.header != newHeader || note.body != newBody) {
                val newNote = note.update(newHeader, newBody, newDateTime)
                noteDAO.update(newNote)
            }
        }

        override suspend fun remove(id: Int): NoteDataModel {
            val note = noteDAO.getNoteByID(id)
            noteDAO.delete(note)

            return mapper.map(note.id, note.header, note.body, note.dateTime, note.lastEditedDateTime)
        }
    }
}