package github.mik0war.hinote.domain

import github.mik0war.hinote.core.Mapper
import java.util.Date

class Note(
    private val id: Int,
    private val header: String,
    private val body: String,
    private var date: Date
){
    fun <T>map(mapper: Mapper<T>) = mapper.map(id, header, body, date)
}

