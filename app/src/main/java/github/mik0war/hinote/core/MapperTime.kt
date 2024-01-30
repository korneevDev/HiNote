package github.mik0war.hinote.core

import github.mik0war.hinote.data.cache.room.Time
import javax.inject.Inject

interface MapperTime<T> {
    fun map(time: Long, editedTime: Long?, noteId: Int=0): T

    class ToTimeModel @Inject constructor() : MapperTime<TimeModel> {
        override fun map(time: Long, editedTime: Long?, noteId: Int) = TimeModel(time, editedTime)
    }

    class ToTimeDB @Inject constructor() : MapperTime<Time> {
        override fun map(time: Long, editedTime: Long?, noteId: Int) =
            Time(time, noteId, editedTime)

    }
}