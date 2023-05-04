package github.mik0war.hinote.presentation

import github.mik0war.hinote.presentation.model.NoteUIModel

interface GetLiveData {
    fun getNotesList() : List<NoteUIModel>
}