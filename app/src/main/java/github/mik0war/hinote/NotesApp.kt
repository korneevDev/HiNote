package github.mik0war.hinote

import android.app.Application
import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.data.cache.MockCacheDataSource
import github.mik0war.hinote.domain.CurrentDateTime
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.domain.ResourceManager
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.NoteViewModel

class NotesApp : Application() {

    lateinit var viewModel : NoteViewModel
    override fun onCreate() {
        super.onCreate()

        viewModel = NoteViewModel.Base(
            NoteInteractor.Base(
                NoteRepository.Base(MockCacheDataSource()
                ),
                ExceptionHandler.Base(ResourceManager.Base(this)),
                MapperParametrised.ToDataModel(),
                CurrentDateTime.Base()
            ),
            NoteLiveData.Base()
        )
    }
}