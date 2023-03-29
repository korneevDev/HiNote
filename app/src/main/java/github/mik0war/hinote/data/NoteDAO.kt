package github.mik0war.hinote.data

import github.mik0war.hinote.core.Mapper
import java.util.*

class NoteDAO(
    private val id: Int,
    private val header: String,
    private val body: String,
    private val date: Date
) {
    fun <T> map(mapper: Mapper<T>) = mapper.map(id, header, body, date)

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is NoteDAO -> this.id == other.id &&
                    this.header == other.header &&
                    this.body == other.body &&
                    this.date == other.date
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + header.hashCode()
        result = 31 * result + body.hashCode()
        result = 31 * result + date.hashCode()
        return result
    }
}