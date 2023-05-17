package github.mik0war.hinote.presentation

import github.mik0war.hinote.presentation.model.NoteUIModel

interface CachedNote {
    fun cacheNote(note: NoteUIModel)
    fun getNote(): NoteUIModel
    fun setObserver(block: () -> Unit)

    class Base: CachedNote {
        private var cachedNote: NoteUIModel? = null
        private lateinit var observeNote: ()-> Unit
        override fun cacheNote(note: NoteUIModel) {
            cachedNote = note
            observeNote.invoke()
        }

        override fun getNote(): NoteUIModel = cachedNote!!.also{cachedNote = null}

        override fun setObserver(block: ()->Unit) {
            observeNote = block
        }
    }
}