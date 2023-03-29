package github.mik0war.hinote.core

import java.util.Date

interface Mapper<T> {
    fun map(id: Int, header: String, body: String, date: Date) : T
}