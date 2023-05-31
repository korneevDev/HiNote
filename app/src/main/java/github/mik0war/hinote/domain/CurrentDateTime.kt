package github.mik0war.hinote.domain

import javax.inject.Inject

interface CurrentDateTime {
    fun getCurrentTime(): Long

    class Base @Inject constructor(): CurrentDateTime{
        override fun getCurrentTime() = System.currentTimeMillis()
    }
}