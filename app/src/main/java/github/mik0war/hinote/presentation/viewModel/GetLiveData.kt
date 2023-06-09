package github.mik0war.hinote.presentation.viewModel

import androidx.recyclerview.widget.DiffUtil
import github.mik0war.hinote.presentation.entity.NoteUIModel

interface GetLiveData {
    fun getNotesList() : List<NoteUIModel>
    fun getDiffUtilResult(): DiffUtil.DiffResult
}