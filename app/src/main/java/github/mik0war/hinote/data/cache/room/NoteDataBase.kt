package github.mik0war.hinote.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Note::class, Time::class],
    version = 1,
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDAO
}

