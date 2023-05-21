package github.mik0war.hinote.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import github.mik0war.hinote.presentation.entity.NoteUIModel
import github.mik0war.hinote.presentation.recyclerView.NoteDiffUtilsCallback
import github.mik0war.hinote.presentation.viewModel.GetLiveData

interface NoteLiveData : GetLiveData {
    fun showNotesList(notesList: List<NoteUIModel>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>)
    fun observe(observer: Observer<List<NoteUIModel>>)


    class Base : NoteLiveData{
        private val notes = MutableLiveData<List<NoteUIModel>>()
        private lateinit var diffResult: DiffUtil.DiffResult

        override fun showNotesList(notesList: List<NoteUIModel>) {
            val callback = NoteDiffUtilsCallback(notes.value ?: emptyList(), notesList)
            diffResult = DiffUtil.calculateDiff(callback)
            notes.value = notesList
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<NoteUIModel>>) {
            notes.observe(owner, observer)
        }

        override fun observe(observer: Observer<List<NoteUIModel>>) {
            notes.observeForever(observer)
        }

        override fun getDiffUtilResult() = diffResult

        override fun getNotesList() = notes.value ?: emptyList()
    }
}