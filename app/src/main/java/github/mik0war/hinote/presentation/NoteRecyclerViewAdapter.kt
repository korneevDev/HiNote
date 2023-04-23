package github.mik0war.hinote.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerViewAdapter(
    private val notesLiveData: NoteLiveData
) : RecyclerView.Adapter<NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount() = notesLiveData.getNotesList().size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
    }


}