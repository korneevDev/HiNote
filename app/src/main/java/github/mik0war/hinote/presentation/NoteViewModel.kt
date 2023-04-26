package github.mik0war.hinote.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.model.NoteUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface NoteViewModel {
    fun getNoteList()
    fun createNote(id: Int, header: String, body: String, date: String)
    fun removeNote(id: Int)
    fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>)

    class Base(
        private val interactor: NoteInteractor,
        private val liveData: NoteLiveData,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    ) : NoteViewModel, ViewModel() {
        override fun getNoteList() {
            viewModelScope.launch(dispatcher) {
                liveData.showNotesList(interactor.getNoteList().map { it.mapTo() })
            }
        }

        override fun createNote(id: Int, header: String, body: String, date: String) {
            viewModelScope.launch(dispatcher) {
                interactor.addNote(id, header, body, date)
            }
        }

        override fun removeNote(id: Int) {
            viewModelScope.launch(dispatcher) {
                interactor.removeNote(id)
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
            liveData.observe(owner, observer)
        }
    }
}