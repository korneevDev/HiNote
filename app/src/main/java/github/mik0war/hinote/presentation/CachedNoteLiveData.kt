package github.mik0war.hinote.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import github.mik0war.hinote.presentation.model.NoteUIModel

interface CachedNoteLiveData {
    fun cacheNote(note: NoteUIModel)
    fun getNote(): NoteUIModel?
    fun observe(owner: LifecycleOwner, observer: Observer<NoteUIModel?>)

    class Base: CachedNoteLiveData {
        private val cachedNote = MutableLiveData<NoteUIModel?>()

        override fun cacheNote(note: NoteUIModel) {
            cachedNote.value = note
        }

        override fun getNote(): NoteUIModel? = cachedNote.value

        override fun observe(owner: LifecycleOwner, observer: Observer<NoteUIModel?>) {
            cachedNote.observe(owner, observer)
        }
    }
}