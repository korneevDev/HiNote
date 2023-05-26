package github.mik0war.hinote.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.mik0war.hinote.NotesApp
import github.mik0war.hinote.R
import github.mik0war.hinote.core.MapperParametrised
import github.mik0war.hinote.data.NoteRepository
import github.mik0war.hinote.data.cache.CacheDataSource
import github.mik0war.hinote.domain.CurrentDateTime
import github.mik0war.hinote.domain.ExceptionHandler
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.domain.ResourceManager
import github.mik0war.hinote.presentation.viewModel.NoteViewModel
import javax.inject.Inject

class NotesActivity : AppCompatActivity() {

    lateinit var viewModel : NoteViewModel

    @Inject
    lateinit var resourceManager: ResourceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NotesApp).appComponent.inject(this)

        val db = (application as NotesApp).db

        viewModel = NoteViewModel.Base(
            NoteInteractor.Base(
                NoteRepository.Base(
                    CacheDataSource.Base(
                    MapperParametrised.ToDataModel(),
                    db.noteDao()
                )
                ),
                ExceptionHandler.Base(resourceManager),
                CurrentDateTime.Base()
            ),
            NoteLiveData.Base()
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
