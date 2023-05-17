package github.mik0war.hinote

import android.app.Application
import androidx.room.Room
import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.data.cache.NoteDatabase
import github.mik0war.hinote.domain.CurrentDateTime
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.domain.ResourceManager
import github.mik0war.hinote.presentation.CachedNote
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.viewModel.NoteViewModel

class NotesApp : Application() {

    lateinit var viewModel : NoteViewModel
    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java, "database-note"
        ).build()


        viewModel = NoteViewModel.Base(
            NoteInteractor.Base(
                NoteRepository.Base(CacheDataSource.Base(
                    MapperParametrised.ToDataModel(),
                    db.noteDao()
                )
                ),
                ExceptionHandler.Base(ResourceManager.Base(this)),
                CurrentDateTime.Base()
            ),
            NoteLiveData.Base(),
            CachedNote.Base()
        )
    }
}