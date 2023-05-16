package github.mik0war.hinote.presentation.model

import github.mik0war.hinote.core.Mapper
import github.mik0war.hinote.domain.model.NoteModel
import github.mik0war.hinote.presentation.CustomTextView
import github.mik0war.hinote.presentation.NoteDeleteClickListener

abstract class NoteUIModel(
    private val text: String,
) : Mapper<NoteModel>{
    fun body() = text
    abstract fun map(headerView: CustomTextView, bodyView: CustomTextView, dateTimeView: CustomTextView)
    open fun same(noteUIModel: NoteUIModel) = false
    open fun matches(id: Int): Boolean = false
    open fun delete(listener: NoteDeleteClickListener){}

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

        override fun same(noteUIModel: NoteUIModel): Boolean =
            noteUIModel is SuccessNoteUIModel && noteUIModel.matches(id)

        override fun matches(id: Int): Boolean = this.id == id

        override fun delete(listener: NoteDeleteClickListener) {
            listener.delete(id)
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