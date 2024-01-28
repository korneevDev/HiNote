package github.mik0war.hinote.core

import github.mik0war.hinote.data.cache.room.Note
import github.mik0war.hinote.data.entity.NoteDataModel
import github.mik0war.hinote.domain.entity.NoteModel
import javax.inject.Inject

interface MapperParametrised<T> {
    fun map(
        id: Int,
        header: String,
        body: String,
        dateTime: Long,
        lastEditedDateTime: Long?,
        mainColor: Int,
        buttonsColor: Int
    ): T

    class ToDataModel @Inject constructor() : MapperParametrised<NoteDataModel> {
        override fun map(
            id: Int,
            header: String,
            body: String,
            dateTime: Long,
            lastEditedDateTime: Long?,
            mainColor: Int,
            buttonsColor: Int
        ) =
            NoteDataModel(id, header, body, dateTime, lastEditedDateTime, mainColor, buttonsColor)
    }

    class ToNoteModel @Inject constructor() : MapperParametrised<NoteModel> {
        override fun map(
            id: Int,
            header: String,
            body: String,
            dateTime: Long,
            lastEditedDateTime: Long?,
            mainColor: Int,
            buttonsColor: Int
        ) = NoteModel.Success(
            id,
            header,
            body,
            dateTime,
            lastEditedDateTime,
            mainColor,
            buttonsColor
        )
    }

    class ToNoteDB @Inject constructor() : MapperParametrised<Note> {
        override fun map(
            id: Int,
            header: String,
            body: String,
            dateTime: Long,
            lastEditedDateTime: Long?,
            mainColor: Int,
            buttonsColor: Int
        ) = Note(header, body, dateTime, lastEditedDateTime, mainColor, buttonsColor).also {
            it.id = id
        }

    }
}