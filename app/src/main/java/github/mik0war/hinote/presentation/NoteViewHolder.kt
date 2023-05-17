package github.mik0war.hinote.presentation

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.R
import github.mik0war.hinote.presentation.model.NoteUIModel

abstract class NoteViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view){
    protected val bodyTextView: CustomTextViewImpl = itemView.findViewById(R.id.noteBody)
    open fun bind(item: NoteUIModel) = item.map(bodyTextView)
    class Base(
        private val listener: NoteDeleteClickListener,
        private val editClickListener: NoteEditClickListener,
        view: View
    ): NoteViewHolder(view){
        private val headerTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteHeader)
        private val dateTimeTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteDateTime)

        private val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
        private val editButton = itemView.findViewById<ImageButton>(R.id.editButton)
        override fun bind(item : NoteUIModel) {
            item.map(headerTextView, bodyTextView, dateTimeTextView)

            deleteButton.setOnClickListener{
                item.delete(listener)
            }

            editButton.setOnClickListener {
                item.getContent(editClickListener)
            }
        }
    }

    class Empty(view: View): NoteViewHolder(view)

}