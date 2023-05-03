package github.mik0war.hinote.presentation

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.R
import github.mik0war.hinote.presentation.model.NoteUIModel

class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val headerTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteHeader)
    private val bodyTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteHeader)
    private val dateTimeTextView = itemView.findViewById<CustomTextViewImpl>(R.id.noteDateTime)
    fun bind(item : NoteUIModel) = item.map(headerTextView, bodyTextView, dateTimeTextView)
}