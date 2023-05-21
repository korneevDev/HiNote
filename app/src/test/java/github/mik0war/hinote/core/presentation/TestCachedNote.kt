package github.mik0war.hinote.core.presentation

import github.mik0war.hinote.presentation.CachedNote
import github.mik0war.hinote.presentation.model.NoteUIModel

class TestCachedNote: CachedNote {
    private var cachedNote: NoteUIModel? = null
    var observeCount = 0
    override fun cacheNote(note: NoteUIModel) {
        cachedNote = note
    }

    override fun getNote() = cachedNote!!

    override fun setObserver(block: () -> Unit) {
        observeCount++
    }
}