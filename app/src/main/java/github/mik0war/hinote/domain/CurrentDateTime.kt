package github.mik0war.hinote.domain

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface CurrentDateTime {
    fun getCurrentTime(): String

    class Base: CurrentDateTime{
        override fun getCurrentTime(): String {
            val sdf = SimpleDateFormat("HH:mm - dd.MM", Locale.getDefault())
            return sdf.format(Date())
        }
    }
}