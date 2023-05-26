package github.mik0war.hinote

import android.app.Application
import github.mik0war.hinote.di.DaggerNoteComponent
import github.mik0war.hinote.di.NoteComponent
import github.mik0war.hinote.di.RoomModule

class NotesApp : Application() {
    val appComponent: NoteComponent by lazy {
        DaggerNoteComponent.builder().roomModule(RoomModule(applicationContext)).build()
    }
}