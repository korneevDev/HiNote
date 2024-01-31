package github.mik0war.hinote.data.cache

import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.core.MapperTime
import github.mik0war.hinote.core.TimeModel
import github.mik0war.hinote.data.MutableCacheDataSource
import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.data.cache.room.NoteDAO
import github.mik0war.hinote.data.cache.room.Time
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

    suspend fun remove(id: Int, mutableCacheDataSource: MutableCacheDataSource<NoteDataModel>)

    class Base @Inject constructor(
        private val mapper: MapperParametrised<NoteDataModel>,
        private val noteDAO: NoteDAO,
        private val mapperToDB: MapperParametrised<Note>,
        private val timeMapper: MapperTime<TimeModel>,
        private val timeMapperToDB: MapperTime<Time>
    ) : CacheDataSource {
        override suspend fun getNotesList(): List<NoteDataModel> {
            val list = noteDAO.getAllOrderedByLastEditedTime().map {
                it.key.map(mapper, it.value.map(timeMapper))
            }
            return list.ifEmpty { throw NoNotesException() }
        }


        override suspend fun save(
            header: String, body: String, dateTime: Long, mainColor: Int, buttonsColor: Int
        ) {
            val id = noteDAO.createNote(Note(header, body, mainColor, buttonsColor))
            noteDAO.createTime(Time(dateTime, id.toInt()))
        }

        override suspend fun save(note: NoteDataModel) {
            noteDAO.createNote(note.map(mapperToDB))
            noteDAO.createTime(note.mapTime(timeMapperToDB))
        }

        override suspend fun update(
            id: Int,
            newHeader: String,
            newBody: String,
            newDateTime: Long,
            newMainColor: Int,
            newButtonsColor: Int
        ) {
            noteDAO.updateNote(
                id, newHeader, newBody,
                newMainColor, newButtonsColor
            )
            noteDAO.updateTime(id, newDateTime)
        }

        override suspend fun remove(
            id: Int,
            mutableCacheDataSource: MutableCacheDataSource<NoteDataModel>
        ) {
            val noteWithTime = noteDAO.getNoteByID(id)
            noteDAO.delete(id)
            noteDAO.deleteTime(id)

            noteWithTime.entries.forEach {
                mutableCacheDataSource.cache(it.key.map(mapper, it.value.map(timeMapper)))
            }
        }
    }
}