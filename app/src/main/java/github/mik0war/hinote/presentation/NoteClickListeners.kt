package github.mik0war.hinote.presentation

interface NoteDeleteClickListener {
    fun delete(id: Int)
}

interface NoteEditClickListener{
    fun edit(content : Pair<String, String>)
}