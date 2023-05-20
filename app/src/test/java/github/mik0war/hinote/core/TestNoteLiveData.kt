package github.mik0war.hinote.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import github.mik0war.hinote.presentation.NoteLiveData
import github.mik0war.hinote.presentation.model.NoteUIModel

class TestNoteLiveData : NoteLiveData {
    var observeCount = 0
    var notesList = ArrayList<NoteUIModel>()

    override fun showNotesList(notesList: List<NoteUIModel>) {
        this.notesList = ArrayList(notesList)
    }

    override fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
        observeCount++
    }

    override fun observe(observer: Observer<List<NoteUIModel>>) {
        observeCount++
    }


    override fun getNotesList(): List<NoteUIModel> = notesList
    override fun getDiffUtilResult(): DiffUtil.DiffResult {
        TODO("Not yet implemented")
    }
}