package github.mik0war.hinote.core.presentation

import github.mik0war.hinote.presentation.DateTimeFormatter

class TestDateFormatter: DateTimeFormatter {
    override fun formatDate(dateTime: Long, lastEditedDateTime: Long?): String = dateTime.toString() + " " + lastEditedDateTime.toString()
}