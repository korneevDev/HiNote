package github.mik0war.hinote.presentation.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.core.ColorResourceManager
import github.mik0war.hinote.databinding.NotesListEmptyObjectBinding
import github.mik0war.hinote.databinding.NotesListObjectBinding
import github.mik0war.hinote.presentation.NoteDeleteClickListener
import github.mik0war.hinote.presentation.NoteEditClickListener
import github.mik0war.hinote.presentation.entity.NoteUIModel
import github.mik0war.hinote.presentation.viewModel.GetLiveData

class NoteRecyclerViewAdapter(
    private val notesLiveData: GetLiveData,
    private val deleteClickListener: NoteDeleteClickListener,
    private val editClickListener: NoteEditClickListener,
    private val colorProvider: ColorResourceManager
) : RecyclerView.Adapter<NoteViewHolder>() {

    fun update() {
        val diffResult = notesLiveData.getDiffUtilResult()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return if (viewType == 0) NoteViewHolder.Empty(
            NotesListEmptyObjectBinding.inflate(
                LayoutInflater.from(parent.context)
            )
        )
        else NoteViewHolder.Base(
            deleteClickListener,
            editClickListener,
            NotesListObjectBinding.inflate(LayoutInflater.from(parent.context)),
            colorProvider
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