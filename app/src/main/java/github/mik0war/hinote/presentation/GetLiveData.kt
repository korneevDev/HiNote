package github.mik0war.hinote.presentation

import androidx.recyclerview.widget.DiffUtil
import github.mik0war.hinote.presentation.model.NoteUIModel

interface GetLiveData {
    fun getNotesList() : List<NoteUIModel>
    fun getDiffUtilResult(): DiffUtil.DiffResult
}