package github.mik0war.hinote

import android.app.Application
import androidx.room.Room
import github.mik0war.hinote.data.cache.NoteDatabase
import github.mik0war.hinote.di.DaggerNoteComponent
import github.mik0war.hinote.di.NoteComponent

class NotesApp : Application() {

    lateinit var db: NoteDatabase

    val appComponent: NoteComponent by lazy {
        DaggerNoteComponent.factory().create(this)
    }
    override fun onCreate() {
        super.onCreate()

        db = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java, "database-note"
        ).build()
    }
}