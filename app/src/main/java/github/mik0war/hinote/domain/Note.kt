package github.mik0war.hinote.domain

import java.util.Date

class Note(
    private val id: Int,
    private val header: String,
    private val body: String,
    private var date: Date
){
    fun map(mapper: Mapper) = mapper.map(id, header, body, date)
}

