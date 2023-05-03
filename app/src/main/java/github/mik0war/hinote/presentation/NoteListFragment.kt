package github.mik0war.hinote.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import github.mik0war.hinote.NotesApp
import github.mik0war.hinote.R

class NoteListFragment : Fragment() {
    private lateinit var viewModel : NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_note_list, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = (requireActivity().application as NotesApp).viewModel

        viewModel.createNote(1, "Header", "body", "00-00-00")
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.notesList)

        recyclerView.adapter = NoteRecyclerViewAdapter(viewModel)
    }
}