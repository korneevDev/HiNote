package github.mik0war.hinote.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import github.mik0war.hinote.presentation.NotesActivity
import javax.inject.Singleton

@Singleton
@Component(modules = [
    BaseModule::class,
    RoomModule::class,
    DispatchersModule::class
])
interface NoteComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): NoteComponent
    }
    fun inject(mainActivity: NotesActivity)
}