package github.mik0war.hinote.core

import github.mik0war.hinote.data.entity.NoteDataModel
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
}