package github.mik0war.hinote.core

import github.mik0war.hinote.data.NoteDataModel
import github.mik0war.hinote.domain.NoteModel
import github.mik0war.hinote.presentation.NoteUIModel
import github.mik0war.hinote.presentation.SuccessNoteUIModel
import java.util.Date

interface Mapper<T> {
    fun map(id: Int = 0, header: String = "", body: String, date: Date = Date(1011200)) : T

    class ToDataModel : Mapper<NoteDataModel> {
        override fun map(id: Int, header: String, body: String, date: Date) =
            NoteDataModel(id, header, body, date)
    }

    class ToNote : Mapper<NoteModel> {
        override fun map(id: Int, header: String, body: String, date: Date) =
            NoteModel.Success(id, header, body, date)
    }

    class ToUISuccessModel : Mapper<NoteUIModel> {
        override fun map(id: Int, header: String, body: String, date: Date) =
            SuccessNoteUIModel(header, body, date)
    }
}