package github.mik0war.hinote.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import github.mik0war.hinote.presentation.NotesActivity

@Component(modules = [BaseModule::class])
interface NoteComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance applicationContext: Context): NoteComponent
    }
    fun inject(mainActivity: NotesActivity)
}