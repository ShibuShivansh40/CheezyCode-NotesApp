package com.example.cheezycode_notesapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheezycode_notesapp.models.NoteRequest
import com.example.cheezycode_notesapp.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


// ViewModel talks to the Repository No
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    val notesLiveData get() = noteRepository.notesLiveData
    val statusLiveData get() = noteRepository.statusLiveData


    fun getNotes(){
        viewModelScope.launch{
            noteRepository.getNotes()
        }
    }

//    fun createNotes(noteRequest: NoteRequest){
//        viewModelScope.launch{
//            noteRepository.createNote(noteRequest)
//        }
//    }

//    fun updateNote(noteId: String, noteRequest: NoteRequest){
//        viewModelScope.launch{
//            noteRepository.updateNote(noteId, noteRequest)
//        }
//    }
//
//    fun deleteNote(noteId: String, noteRequest: NoteRequest){
//        viewModelScope.launch{
//            noteRepository.deleteNote(noteId)
//        }
//    }



}