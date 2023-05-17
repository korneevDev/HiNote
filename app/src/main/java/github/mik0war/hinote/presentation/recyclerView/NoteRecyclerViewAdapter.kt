package github.mik0war.hinote.presentation.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.R
import github.mik0war.hinote.presentation.viewModel.GetLiveData
import github.mik0war.hinote.presentation.NoteDeleteClickListener
import github.mik0war.hinote.presentation.NoteEditClickListener
import github.mik0war.hinote.presentation.model.NoteUIModel

class NoteRecyclerViewAdapter(
    private val notesLiveData: GetLiveData,
    private val listener: NoteDeleteClickListener,
    private val editClickListener: NoteEditClickListener
) : RecyclerView.Adapter<NoteViewHolder>() {

    fun update() {
        val diffResult = notesLiveData.getDiffUtilResult()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val isEmptyList = viewType == 0

        val layout = if (isEmptyList)
            R.layout.notes_list_empty_object
        else
            R.layout.notes_list_object

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return if (isEmptyList) NoteViewHolder.Empty(view)
            else NoteViewHolder.Base(
            listener, editClickListener, view
        )
    }

    override fun getItemCount() = notesLiveData.getNotesList().size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notesLiveData.getNotesList()[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (notesLiveData.getNotesList()[position]) {
            is NoteUIModel.Failed -> 0
            else -> 1
        }
    }
}