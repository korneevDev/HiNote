package github.mik0war.hinote.core

import github.mik0war.hinote.presentation.DateTimeFormatter


data class TimeModel(
    private val createdDateTime: Long,
    private val lastEditedDateTime: Long? = null
) {
    fun <T> map(mapperTime: MapperTime<T>, noteId: Int) =
        mapperTime.map(createdDateTime, lastEditedDateTime, noteId)

    fun formatDateTime(dateTimeFormatter: DateTimeFormatter): String =
        dateTimeFormatter.formatDate(createdDateTime, lastEditedDateTime)

}