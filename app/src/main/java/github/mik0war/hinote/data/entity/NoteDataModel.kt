package github.mik0war.hinote.data.entity

import github.mik0war.hinote.core.MapperParametrised

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val dateTime: Long,
    private val lastEditedDateTime: Long?,
    private val mainColor: Int,
    private val buttonsColor: Int
) {

    fun <T> map(mapper: MapperParametrised<T>) =
        mapper.map(id, header, body, dateTime, lastEditedDateTime, mainColor, buttonsColor)
}