package github.mik0war.hinote.presentation.model

import github.mik0war.hinote.core.Mapper
import github.mik0war.hinote.domain.model.NoteModel

abstract class NoteUIModel(
    private val text: String,
) : Mapper<NoteModel>{
    fun body() = text
    class SuccessNoteUIModel(
        private val id: Int,
        private val header: String,
        body: String,
        private val dateTime: String
    ) : NoteUIModel(body) {
        override fun mapTo(): NoteModel = NoteModel.Success(id, header, body(), dateTime)
    }

    class Failed(body: String) : NoteUIModel(body) {
        override fun mapTo() = NoteModel.Failed(body())
    }
}