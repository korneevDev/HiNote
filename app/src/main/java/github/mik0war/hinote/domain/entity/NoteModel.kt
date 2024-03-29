package github.mik0war.hinote.domain.entity

import github.mik0war.hinote.core.TimeModel
import github.mik0war.hinote.presentation.DateTimeFormatter
import github.mik0war.hinote.presentation.entity.NoteUIModel

sealed interface NoteModel {
    fun mapTo(dateTimeFormatter: DateTimeFormatter): NoteUIModel
    data class Success(
        private val id: Int,
        private val header: String,
        private val body: String,
        private var dateTime: TimeModel,
        private val mainColor: Int,
        private val buttonsColor: Int
    ) : NoteModel {
        override fun mapTo(dateTimeFormatter: DateTimeFormatter) =
            NoteUIModel.Success(
                id, header, body,
                dateTime.formatDateTime(dateTimeFormatter), mainColor, buttonsColor
            )
    }

    data class Failed(
        private val errorMessage: String
    ) : NoteModel {
        override fun mapTo(dateTimeFormatter: DateTimeFormatter) = NoteUIModel.Failed(errorMessage)
    }
}

