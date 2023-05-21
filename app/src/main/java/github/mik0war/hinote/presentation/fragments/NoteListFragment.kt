package github.mik0war.hinote.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import github.mik0war.hinote.NotesApp
import github.mik0war.hinote.R
import github.mik0war.hinote.presentation.NoteDeleteClickListener
import github.mik0war.hinote.presentation.NoteEditClickListener
import github.mik0war.hinote.presentation.recyclerView.NoteRecyclerViewAdapter
import github.mik0war.hinote.presentation.viewModel.NoteViewModel

class NoteListFragment : Fragment() {
    private lateinit var viewModel : NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_note_list, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as NotesApp).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.notesList)

        val adapter = NoteRecyclerViewAdapter(viewModel, object : NoteDeleteClickListener {
            override fun delete(id: Int) {
                viewModel.removeNote(id)
                Snackbar.make(
                    recyclerView,
                    R.string.undo_question,
                    Snackbar.LENGTH_SHORT
                ).setAction(R.string.undo_answer) {
                    viewModel.undoDelete()
                }.show()
            }
        },
        object : NoteEditClickListener {
            override fun edit(content: Triple<Int, String, String>) {
                val bundle = Bundle()
                bundle.putInt(KeyNames.ID_NAME.keyName, content.first)
                bundle.putString(KeyNames.HEADER_NAME.keyName, content.second)
                bundle.putString(KeyNames.BODY_NAME.keyName, content.third)

                findNavController().navigate(R.id.action_NotesListFragment_to_CreateNoteFragment, bundle)
            }

        })

        viewModel.observeList(this){
            adapter.update()
        }

        recyclerView.adapter = adapter
        viewModel.showNoteList()

        val createButton = view.findViewById<FloatingActionButton>(R.id.createButton)

        createButton.setOnClickListener{
            findNavController().navigate(R.id.action_NotesListFragment_to_CreateNoteFragment)
        }
    }
}

enum class KeyNames(val keyName: String){
    ID_NAME("id"),
    HEADER_NAME("header"),
    BODY_NAME("body")
}