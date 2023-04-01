package github.mik0war.hinote.data

import github.mik0war.hinote.core.Mapper
import java.util.*

data class NoteDataModel(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val date: Date
) {
    fun <T> map(mapper: Mapper<T>) = mapper.map(id, header, body, date)
}