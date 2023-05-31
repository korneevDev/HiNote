package github.mik0war.hinote.presentation.entity

import github.mik0war.hinote.presentation.CustomTextView
import github.mik0war.hinote.presentation.NoteDeleteClickListener
import github.mik0war.hinote.presentation.NoteEditClickListener

abstract class NoteUIModel(
    private val text: String,
){
    fun body() = text
    open fun map(headerView: CustomTextView, bodyView: CustomTextView, dateTimeView: CustomTextView) {
        map(bodyView)
    }
    fun map(bodyView: CustomTextView){
        bodyView.show(text)
    }

    open fun same(noteUIModel: NoteUIModel) = false
    open fun matches(id: Int): Boolean = false
    open fun delete(listener: NoteDeleteClickListener){}
    open fun getContent(editClickListener: NoteEditClickListener){}

    data class Success(
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
            map(bodyView)
            headerView.show(header)
            dateTimeView.show(dateTime)
        }

        override fun same(noteUIModel: NoteUIModel): Boolean =
            noteUIModel is Success && noteUIModel.matches(id)

        override fun matches(id: Int): Boolean = this.id == id

        override fun delete(listener: NoteDeleteClickListener) {
            listener.delete(id)
        }

        override fun getContent(editClickListener: NoteEditClickListener) {
            editClickListener.edit(Triple(id, header, body))
        }
    }

    data class Failed(val body: String) : NoteUIModel(body)
}