package github.mik0war.hinote.presentation

import android.view.View
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.R
import github.mik0war.hinote.presentation.model.NoteUIModel

class NoteViewHolder(
    private val listener: NoteDeleteClickListener,
    private val editClickListener: NoteEditClickListener,
    view: View,
) : RecyclerView.ViewHolder(view){
    private val headerTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteHeader)
    private val bodyTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteBody)
    private val dateTimeTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteDateTime)

    private val deleteButton = itemView.findViewById<ImageButton>(R.id.deleteButton)
    private val editButton = itemView.findViewById<ImageButton>(R.id.editButton)
    fun bind(item : NoteUIModel) {
        item.map(headerTextView, bodyTextView, dateTimeTextView)

        deleteButton.setOnClickListener{
            item.delete(listener)
        }

        editButton.setOnClickListener {
            item.getContent(editClickListener)
        }
    }
}