package github.mik0war.hinote.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import github.mik0war.hinote.NotesApp
import github.mik0war.hinote.R
import github.mik0war.hinote.presentation.NoteViewModel

class NoteCreateFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_create_note, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity().application as NotesApp).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<FloatingActionButton>(R.id.createButton)

        val headerEditText = view.findViewById<EditText>(R.id.noteHeader)
        val bodyEditText = view.findViewById<EditText>(R.id.noteBody)

        headerEditText.setText(arguments?.getString(KeyNames.HEADER_NAME.keyName))
        bodyEditText.setText(arguments?.getString(KeyNames.BODY_NAME.keyName))

        button.setOnClickListener{
            val headerText = headerEditText.text.toString()
            val bodyText = bodyEditText.text.toString()
            if(arguments == null)
                viewModel.createNote(headerText, bodyText)
            else
                viewModel.updateNote(
                    requireArguments().getInt(KeyNames.ID_NAME.keyName), headerText, bodyText
                )
            findNavController().navigate(R.id.action_CreateNoteFragment_to_NotesListFragment)
        }
    }
}