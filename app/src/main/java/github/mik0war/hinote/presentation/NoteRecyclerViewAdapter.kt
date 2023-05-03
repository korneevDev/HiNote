package github.mik0war.hinote.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.R

class NoteRecyclerViewAdapter(
    private val notesLiveData: GetLiveData
) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.notes_list_object,
            parent, false))
    }

    override fun getItemCount() = notesLiveData.getNotesList().size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notesLiveData.getNotesList()[position])
    }


}