package com.example.cheezycode_notesapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cheezycode_notesapp.api.NoteResponse2
import com.example.cheezycode_notesapp.databinding.NoteItemBinding
import com.example.cheezycode_notesapp.models.NoteResponse

//class NoteAdapter(private val onNoteClicked: (NoteResponse) -> Unit) :
class NoteAdapter() :


//    ListAdapter<NoteResponse, NoteAdapter.NoteViewHolder>(ComparatorDiffUtil()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
//        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return NoteViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
//        val note = getItem(position)
//        note?.let {
//            holder.bind(it)
//        }
//    }
//
//    inner class NoteViewHolder(private val binding: NoteItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(note: NoteResponse) {
//            binding.title.text = note.posts.title
//            binding.desc.text = note.posts.description
//            binding.root.setOnClickListener {
////                onNoteClicked(note)
//            }
//        }
//
//    }

//    class ComparatorDiffUtil : DiffUtil.ItemCallback<NoteResponse>() {
////        override fun areItemsTheSame(oldItem: NoteResponse, newItem: NoteResponse): Boolean {
////        }
////
////        override fun areContentsTheSame(oldItem: NoteResponse, newItem: NoteResponse): Boolean {
////        }
//    }
}