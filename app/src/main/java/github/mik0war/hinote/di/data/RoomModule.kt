package github.mik0war.hinote.di.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import github.mik0war.hinote.data.cache.room.NoteDatabase
import javax.inject.Singleton

@Module
class RoomModule {
    companion object{
        const val DB_NAME = "database-note"
    }
    @Singleton
    @Provides
    fun provideRoomDB(context: Context) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java, DB_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRoomDAO(db: NoteDatabase) = db.noteDao()

}