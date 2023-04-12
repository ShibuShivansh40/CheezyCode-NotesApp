package com.example.cheezycode_notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cheezycode_notesapp.databinding.FragmentMainBinding
import com.example.cheezycode_notesapp.utils.NetworkResult
import com.example.cheezycode_notesapp.utils.NoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class mainFragment : Fragment() {

//    @Inject
//    lateinit var  notesAPI: NotesAPI

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val noteViewModel by viewModels<NoteViewModel>()

    private lateinit var adapter : NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater , container, false)
        adapter = NoteAdapter()
        return binding.root
//        CoroutineScope(Dispatchers.IO).launch{
//            val response = NotesAPI.getNotes()
//            Log.d(TAG, response.body().toString())
//        }

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObservers()
        noteViewModel.getNotes()
        binding.noteList.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        binding.noteList.adapter = adapter
    }

    private fun bindObservers() {
        noteViewModel.notesLiveData.observe(viewLifecycleOwner , Observer{
            binding.progressBar.isVisible = false
            when(it){
                is NetworkResult.Success -> {//Here a list of notes will be rendered
                    adapter.submitList(it.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}