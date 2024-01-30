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
        time: TimeModel,
        mainColor: Int,
        buttonsColor: Int
    ): T

    class ToDataModel @Inject constructor() : MapperParametrised<NoteDataModel> {
        override fun map(
            id: Int,
            header: String,
            body: String,
            time: TimeModel,
            mainColor: Int,
            buttonsColor: Int
        ) =
            NoteDataModel(id, header, body, time, mainColor, buttonsColor)
    }

    class ToNoteModel @Inject constructor() : MapperParametrised<NoteModel> {
        override fun map(
            id: Int,
            header: String,
            body: String,
            time: TimeModel,
            mainColor: Int,
            buttonsColor: Int
        ) = NoteModel.Success(
            id,
            header,
            body,
            time,
            mainColor,
            buttonsColor
        )
    }

    class ToNoteDB @Inject constructor() : MapperParametrised<Note> {
        override fun map(
            id: Int,
            header: String,
            body: String,
            time: TimeModel,
            mainColor: Int,
            buttonsColor: Int
        ) = Note(header, body, mainColor, buttonsColor).also {
                it.id = id
            }

    }

}