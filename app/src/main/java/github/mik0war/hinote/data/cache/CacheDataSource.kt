package github.mik0war.hinote.data.cache

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.data.cache.room.NoteDAO
import github.mik0war.hinote.data.entity.NoteDataModel
import github.mik0war.hinote.domain.NoNotesException
import javax.inject.Inject

interface CacheDataSource {
    suspend fun getNotesList(): List<NoteDataModel>
    suspend fun save(
        header: String, body: String, dateTime: Long, mainColor: Int, buttonsColor: Int
    )

    suspend fun save(note: NoteDataModel)
    suspend fun update(
        id: Int,
        newHeader: String,
        newBody: String,
        newDateTime: Long,
        newMainColor: Int,
        newButtonsColor: Int
    )

    suspend fun remove(id: Int): NoteDataModel

    class Base @Inject constructor(
        private val mapper: MapperParametrised<NoteDataModel>, private val noteDAO: NoteDAO
    ) : CacheDataSource {
        override suspend fun getNotesList(): List<NoteDataModel> {
            val list = noteDAO.getAllOrderedByLastEditedTime().map {
                mapper.map(
                    it.id,
                    it.header,
                    it.body,
                    it.dateTime,
                    it.lastEditedDateTime,
                    it.mainColor,
                    it.buttonsColor
                )
            }
            return list.ifEmpty { throw NoNotesException() }
        }


        override suspend fun save(
            header: String, body: String, dateTime: Long, mainColor: Int, buttonsColor: Int
        ) {
            noteDAO.createNote(Note(header, body, dateTime, null, mainColor, buttonsColor))
        }

        override suspend fun save(note: NoteDataModel) {
            noteDAO.createNote(note.mapToNote())
        }

        override suspend fun update(
            id: Int,
            newHeader: String,
            newBody: String,
            newDateTime: Long,
            newMainColor: Int,
            newButtonsColor: Int
        ) {
            noteDAO.update(
                id, newHeader, newBody,
                newMainColor, newButtonsColor,
                newDateTime
            )
        }

        override suspend fun remove(id: Int): NoteDataModel {
            val note = noteDAO.getNoteByID(id)
            noteDAO.delete(id)

            return mapper.map(
                note.id,
                note.header,
                note.body,
                note.dateTime,
                note.lastEditedDateTime,
                note.mainColor,
                note.buttonsColor
            )
        }
    }
}