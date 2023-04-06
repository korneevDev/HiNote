package github.mik0war.hinote.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import github.mik0war.hinote.presentation.model.NoteUIModel

interface NotesLiveData {
    fun showNotesList(notesList: List<NoteUIModel>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>)

    class Base : NotesLiveData{
        private val notes = MutableLiveData<List<NoteUIModel>>()

        override fun showNotesList(notesList: List<NoteUIModel>) {
            notes.value = notesList
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
            notes.observe(owner, observer)
        }

    }
}