package github.mik0war.hinote.data

import github.mik0war.hinote.core.Mapper

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val dateTime: String
) {
    fun <T> map(mapper: Mapper<T>) = mapper.map(id, header, body, dateTime)
}