package github.mik0war.hinote.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import github.mik0war.hinote.data.cache.room.NoteDatabase
import javax.inject.Singleton

@Module
class RoomModule (private val context: Context) {
    private val db: NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java, "database-note"
    )
        .build()
    @Singleton
    @Provides
    fun provideRoomDB() = db

    @Singleton
    @Provides
    fun provideRoomDAO() = db.noteDao()

    @Provides
    fun provideContext() = context

}