package github.mik0war.hinote.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import github.mik0war.hinote.NotesApp
import github.mik0war.hinote.R
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.viewModel.NoteViewModel
import javax.inject.Inject

class NotesActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : NoteViewModel

    @Inject
    lateinit var interactor: NoteInteractor
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NotesApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
