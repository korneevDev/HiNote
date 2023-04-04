package github.mik0war.hinote.core

import github.mik0war.hinote.data.model.NoteDataModel

interface MapperParametrised<T> {
    fun map(id: Int = 0, header: String = "", body: String, dateTime: String = "") : T

    class ToDataModel : MapperParametrised<NoteDataModel> {
        override fun map(id: Int, header: String, body: String, dateTime: String) =
            NoteDataModel(id, header, body, dateTime)
    }
}