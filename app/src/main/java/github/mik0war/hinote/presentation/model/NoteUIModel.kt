package github.mik0war.hinote.presentation.model

import github.mik0war.hinote.core.Mapper
import github.mik0war.hinote.domain.model.NoteModel
import github.mik0war.hinote.presentation.CustomTextView

abstract class NoteUIModel(
    private val text: String,
) : Mapper<NoteModel>{
    fun body() = text
    abstract fun map(headerView: CustomTextView, bodyView: CustomTextView, dateTimeView: CustomTextView)
    data class SuccessNoteUIModel(
        private val id: Int,
        private val header: String,
        val body: String,
        private val dateTime: String
    ) : NoteUIModel(body) {
        override fun map(
            headerView: CustomTextView,
            bodyView: CustomTextView,
            dateTimeView: CustomTextView
        ) {
            headerView.show(header)
            bodyView.show(body)
            dateTimeView.show(dateTime)
        }

        override fun mapTo(): NoteModel = NoteModel.Success(id, header, body(), dateTime)
    }

    data class Failed(val body: String) : NoteUIModel(body) {
        override fun map(
            headerView: CustomTextView,
            bodyView: CustomTextView,
            dateTimeView: CustomTextView
        ) {
            bodyView.show(body)
        }

        override fun mapTo() = NoteModel.Failed(body())
    }
}