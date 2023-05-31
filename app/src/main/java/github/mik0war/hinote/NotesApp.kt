package github.mik0war.hinote

import android.app.Application
import github.mik0war.hinote.di.DaggerNoteComponent
import github.mik0war.hinote.di.NoteComponent

class NotesApp : Application() {
    val appComponent: NoteComponent by lazy {
        DaggerNoteComponent.factory().create(this)
    }
}