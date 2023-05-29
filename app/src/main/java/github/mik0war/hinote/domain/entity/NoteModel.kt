package github.mik0war.hinote.domain.entity

import github.mik0war.hinote.presentation.DateTimeFormatter
import github.mik0war.hinote.presentation.entity.NoteUIModel

sealed interface NoteModel {
    fun mapTo(dateTimeFormatter: DateTimeFormatter): NoteUIModel
    data class Success(
        private val id: Int,
        private val header: String,
        private val body: String,
        private var dateTime: String,
        private val lastEditedDateTime: String?
    ) : NoteModel {
        override fun mapTo(dateTimeFormatter: DateTimeFormatter) =
            NoteUIModel.Success(id, header, body,
                dateTimeFormatter.formatDate(dateTime.toLong(), lastEditedDateTime?.toLong()))
    }

    data class Failed(
        private val error_message: String
    ) : NoteModel {
        override fun mapTo(dateTimeFormatter: DateTimeFormatter) = NoteUIModel.Failed(error_message)
    }
}

