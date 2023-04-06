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

interface NoteViewModel{
    fun getNotesList()
    fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>)

    class Base(
        private val interactor: NoteInteractor,
        private val liveData: NotesLiveData,
        private val dispatcher: CoroutineDispatcher = Dispatchers.Main
    ) : NoteViewModel, ViewModel() {
        override fun getNotesList(){
            viewModelScope.launch(dispatcher) {
                liveData.showNotesList(interactor.getNoteList().map { it.mapTo() })
            }
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
            liveData.observe(owner, observer)
        }
    }
}