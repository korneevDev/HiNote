package github.mik0war.hinote.presentation

import github.mik0war.hinote.presentation.model.NoteUIModel

interface CachedNote {
    fun cacheNote(note: NoteUIModel)
    fun getNote(): NoteUIModel
    fun observe(block: () -> Unit)

    class Base: CachedNote {
        private var cachedNote: NoteUIModel? = null
        private lateinit var observe: ()-> Unit
        override fun cacheNote(note: NoteUIModel) {
            cachedNote = note
            observe.invoke()
        }

        override fun getNote(): NoteUIModel = cachedNote!!.also{cachedNote = null}

        override fun observe(block: ()->Unit) {
            observe = block
        }
    }
}