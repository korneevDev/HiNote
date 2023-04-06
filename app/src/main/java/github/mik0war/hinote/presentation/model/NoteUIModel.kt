package github.mik0war.hinote.presentation.model

import github.mik0war.hinote.core.Mapper
import github.mik0war.hinote.domain.model.NoteModel

abstract class NoteUIModel(
    private val text: String,
) : Mapper<NoteModel>{
    fun body() = text
    data class SuccessNoteUIModel(
        private val id: Int,
        private val header: String,
        val body: String,
        private val dateTime: String
    ) : NoteUIModel(body) {
        override fun mapTo(): NoteModel = NoteModel.Success(id, header, body(), dateTime)
    }

    data class Failed(val body: String) : NoteUIModel(body) {
        override fun mapTo() = NoteModel.Failed(body())
    }
}