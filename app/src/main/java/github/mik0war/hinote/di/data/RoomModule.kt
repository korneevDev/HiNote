package github.mik0war.hinote.di.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.mik0war.hinote.data.cache.room.NoteDatabase

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    fun provideRoomDB(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        NoteDatabase::class.java, DbParams.DB_NAME
    ).build()


    @Provides
    fun provideRoomDAO(db: NoteDatabase) = db.noteDao()

}

object DbParams{
    const val DB_NAME = "database-note"
}