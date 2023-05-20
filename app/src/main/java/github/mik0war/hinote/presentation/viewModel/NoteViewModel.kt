package github.mik0war.hinote.presentation.viewModel

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.mik0war.hinote.domain.NoteInteractor
import github.mik0war.hinote.presentation.CachedNote
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.model.NoteUIModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

interface NoteViewModel : GetLiveData {
    fun showNoteList(): Job
    fun createNote(header: String, body: String): Job
    fun createNote(header: String, body: String, dateTime: String): Job
    fun updateNote(id: Int, header: String, body: String): Job
    fun removeNote(id: Int): Job
    fun observeList(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>)
    fun getCached(): NoteUIModel

    fun observeCachedNote(observer: () -> Unit)

    class Base(
        private val interactor: NoteInteractor,
        private val liveData: NoteLiveData,
        private val cacheLiveData: CachedNote,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    ) : NoteViewModel, ViewModel() {
        override fun showNoteList() =
            viewModelScope.launch(dispatcher) {
                liveData.showNotesList(interactor.getNoteList().map { it.mapTo() })
            }

        override fun createNote(header: String, body: String) =
            viewModelScope.launch(dispatcher) {
                interactor.addNote(header, body)
                showNoteList()
            }

        override fun createNote(header: String, body: String, dateTime: String) =
            viewModelScope.launch(dispatcher) {
                interactor.addNote(header, body, dateTime)
                showNoteList()
            }

        override fun updateNote(id: Int, header: String, body: String) =
            viewModelScope.launch(dispatcher) {
                interactor.updateNote(id, header, body)
                showNoteList()
            }

        override fun removeNote(id: Int) =
            viewModelScope.launch(dispatcher) {
                cacheLiveData.cacheNote(interactor.removeNote(id).mapTo())
                showNoteList()
            }

        override fun observeList(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
            liveData.observe(owner, observer)
        }

        override fun getCached(): NoteUIModel =
            cacheLiveData.getNote()

        override fun observeCachedNote(observer: ()-> Unit) {
            cacheLiveData.setObserver(observer)
        }

        override fun getNotesList(): List<NoteUIModel> = liveData.getNotesList()
        override fun getDiffUtilResult() = liveData.getDiffUtilResult()
    }
}