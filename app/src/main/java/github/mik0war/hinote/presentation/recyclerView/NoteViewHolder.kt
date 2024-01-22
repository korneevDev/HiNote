package github.mik0war.hinote.presentation.recyclerView

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.databinding.NotesListEmptyObjectBinding
import github.mik0war.hinote.databinding.NotesListObjectBinding
import github.mik0war.hinote.presentation.NoteDeleteClickListener
import github.mik0war.hinote.presentation.NoteEditClickListener
import github.mik0war.hinote.presentation.entity.NoteUIModel

abstract class NoteViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: NoteUIModel)
    class Base(
        private val listener: NoteDeleteClickListener,
        private val editClickListener: NoteEditClickListener,
        private val binding: NotesListObjectBinding
    ) : NoteViewHolder(binding.root) {
        override fun bind(item: NoteUIModel) {
            itemView.backgroundTintList = ColorStateList.valueOf(Color.BLUE)
            item.map(binding.noteHeader, binding.noteBody, binding.noteDateTime)

            binding.deleteButton.setOnClickListener {
                item.delete(listener)
            }

            binding.editButton.setOnClickListener {
                item.getContent(editClickListener)
            }
        }
    }

    class Empty(private val binding: NotesListEmptyObjectBinding) : NoteViewHolder(binding.root){
        override fun bind(item: NoteUIModel)  = item.map(binding.noteBody)

    }

}