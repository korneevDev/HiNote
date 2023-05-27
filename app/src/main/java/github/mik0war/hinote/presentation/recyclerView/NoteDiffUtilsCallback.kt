package github.mik0war.hinote.presentation.recyclerView

import androidx.recyclerview.widget.DiffUtil
import github.mik0war.hinote.presentation.entity.NoteUIModel

class NoteDiffUtilsCallback(
    private val oldList: List<NoteUIModel>,
    private val newList: List<NoteUIModel>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].same(newList[newItemPosition])

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}