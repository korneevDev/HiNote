package github.mik0war.hinote.di

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import github.mik0war.hinote.data.cache.room.NoteDatabase
import javax.inject.Singleton

@Module
class RoomModule (private val context: Context) {
    val MIGRATION_1_2 = object : Migration(1, 2){
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE note ADD COLUMN lastEditedDateTime TEXT NUll")
        }

    }
    private val db: NoteDatabase = Room.databaseBuilder(
        context,
        NoteDatabase::class.java, "database-note"
    )
        .addMigrations(MIGRATION_1_2)
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