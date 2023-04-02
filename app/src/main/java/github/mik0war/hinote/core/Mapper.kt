package github.mik0war.hinote.core

import github.mik0war.hinote.data.NoteDataModel
import github.mik0war.hinote.domain.NoteModel
import github.mik0war.hinote.presentation.NoteUIModel
import github.mik0war.hinote.presentation.SuccessNoteUIModel

interface Mapper<T> {
    fun map(id: Int = 0, header: String = "", body: String, dateTime: String = "") : T

    class ToDataModel : Mapper<NoteDataModel> {
        override fun map(id: Int, header: String, body: String, dateTime: String) =
            NoteDataModel(id, header, body, dateTime)
    }

    class ToNote : Mapper<NoteModel> {
        override fun map(id: Int, header: String, body: String, dateTime: String) =
            NoteModel.Success(id, header, body, dateTime)
    }

    class ToUISuccessModel : Mapper<NoteUIModel> {
        override fun map(id: Int, header: String, body: String, dateTime: String) =
            SuccessNoteUIModel(header, body, dateTime)
    }
}