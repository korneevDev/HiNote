package github.mik0war.hinote.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.model.NoteUIModel

class MockNoteLiveData : NoteLiveData {
    var observeCount = 0
    var notesList = ArrayList<NoteUIModel>()

    override fun showNotesList(notesList: List<NoteUIModel>) {
        this.notesList = ArrayList(notesList)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
        observeCount++
    }

    override fun getNotesList(): List<NoteUIModel> = notesList
}