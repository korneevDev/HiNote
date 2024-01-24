package github.mik0war.hinote.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import github.mik0war.hinote.R
import github.mik0war.hinote.databinding.FragmentCreateNoteBinding
import github.mik0war.hinote.presentation.NotesActivity
import github.mik0war.hinote.presentation.viewModel.NoteViewModel

class NoteCreateFragment : Fragment() {
    private lateinit var viewModel: NoteViewModel
    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCreateNoteBinding.inflate(inflater).also { _binding = it }.root

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (requireActivity() as NotesActivity).viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.noteHeader.setText(arguments?.getString(KeyNames.HEADER_NAME.keyName))
        binding.noteBody.setText(arguments?.getString(KeyNames.BODY_NAME.keyName))

        binding.createButton.setOnClickListener{
            val headerText = binding.noteHeader.text.toString()
            val bodyText = binding.noteBody.text.toString()
            if(arguments == null)
                viewModel.createNote(headerText, bodyText)
            else
                viewModel.updateNote(
                    requireArguments().getInt(KeyNames.ID_NAME.keyName), headerText, bodyText
                )
            findNavController().navigate(R.id.action_CreateNoteFragment_to_NotesListFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}