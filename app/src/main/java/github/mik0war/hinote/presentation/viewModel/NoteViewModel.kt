package github.mik0war.hinote.presentation.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.entity.NoteUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface NoteViewModel : GetLiveData {
    fun showNoteList(): Job
    fun createNote(header: String, body: String): Job
    fun updateNote(id: Int, header: String, body: String): Job
    fun removeNote(id: Int): Job
    fun observeList(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>)
    fun undoDeleting(): Job

    class Base(
        private val interactor: NoteInteractor,
        private val liveData: NoteLiveData,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    ) : NoteViewModel, ViewModel() {
        override fun showNoteList() =
            viewModelScope.launch(dispatcher) {
                liveData.showNotesList(interactor.getNoteList().map { it.mapTo() })
            }

        override fun createNote(header: String, body: String) = handle { interactor.addNote(header, body) }

        override fun updateNote(id: Int, header: String, body: String) =
            handle { interactor.updateNote(id, header, body) }

        override fun removeNote(id: Int) = handle { interactor.removeNote(id) }

        override fun undoDeleting(): Job = handle { interactor.undoDeletingNote() }
        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
            liveData.observe(owner, observer)
        }

        override fun getNotesList(): List<NoteUIModel> = liveData.getNotesList()
        override fun getDiffUtilResult() = liveData.getDiffUtilResult()

        private inline fun handle(crossinline block: suspend ()-> Unit): Job =
            viewModelScope.launch(dispatcher) {
                block.invoke()
                showNoteList()
            }
    }
}