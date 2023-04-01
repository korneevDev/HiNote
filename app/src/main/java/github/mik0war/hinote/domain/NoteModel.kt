package github.mik0war.hinote.domain

import github.mik0war.hinote.core.Mapper
import java.util.Date

interface NoteModel{
    fun <T>map(mapper: Mapper<T>) : T

    data class Success(
        private val id: Int,
        private val header: String,
        private val body: String,
        private var date: Date
    ) : NoteModel {
        override fun <T> map(mapper: Mapper<T>) =
            mapper.map(id, header, body, date)

    }

    data class Failed(
        private val error_message: String
    ) : NoteModel {
        override fun <T> map(mapper: Mapper<T>): T = mapper.map(body = error_message)
    }
}

