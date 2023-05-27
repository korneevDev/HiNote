package github.mik0war.hinote.domain.entity

import github.mik0war.hinote.core.Mapper
import github.mik0war.hinote.presentation.entity.NoteUIModel

interface NoteModel : Mapper<NoteUIModel> {
    data class Success(
        private val id: Int,
        private val header: String,
        private val body: String,
        private var dateTime: String,
        private val lastEditedDateTime: String?
    ) : NoteModel {
        override fun mapTo() = NoteUIModel.Success(id, header, body, dateTimeToUI())

        private fun dateTimeToUI(): String{
            val (date, time) = lastEditedDateTime?.split(" ") ?: dateTime.split(" ")

            return "$time ${date.subSequence(5, date.length)}" +
                    if(lastEditedDateTime == null) " (edited)" else ""
        }
    }

    data class Failed(
        private val error_message: String
    ) : NoteModel {
        override fun mapTo() = NoteUIModel.Failed(error_message)
    }
}

