package github.mik0war.hinote.di

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
    fun inject(mainActivity: NotesActivity)
}