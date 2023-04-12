package com.example.cheezycode_notesapp.repository

import android.net.Network
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cheezycode_notesapp.api.NotesAPI
import com.example.cheezycode_notesapp.models.NoteRequest
import com.example.cheezycode_notesapp.models.NoteResponse
import com.example.cheezycode_notesapp.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class NoteRepository @Inject constructor(private val notesAPI: NotesAPI){

    private val _notesLiveData = MutableLiveData<NetworkResult<List<NoteResponse>>>()
//    All API Responses will be handled using Network Result. Here data will be set
    val notesLiveData : LiveData<NetworkResult<List<NoteResponse>>>
    //Here the data will be read
    get() = _notesLiveData

    // To get Status
    private val _statusLiveData = MutableLiveData<NetworkResult<String>>()
    val statusLiveData : LiveData<NetworkResult<String>>
    get() = _statusLiveData

    //It will access all the notes. An API Request will occur and data will be pushed to LIVE DATA
    suspend fun getNotes(){
        _notesLiveData.postValue(NetworkResult.Loading()) // Loading State
        val response = notesAPI.getNotes()

        //Error Handling Code
        if (response.isSuccessful && response.body() != null) {
            _notesLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
//            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            //            Here we're parsing the JSON with Array
            _notesLiveData.postValue(NetworkResult.Error("SOMETHING WENT WRONG!"))
//            This tells us that we're unable to read the Error Message , so we will be commenting this line of up
//            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//            Right now, it is creating an error : -     org.json.JSONException: Value Tunnel of type java.lang.String cannot be converted to JSONObject
        } else {
            _notesLiveData.postValue(NetworkResult.Error("Something went Wrong!"))
        }
    }
//All the functions below aren't required according to the Application we're building
//    suspend fun createNote(noteRequest: NoteRequest){
//        _statusLiveData.postValue(NetworkResult.Loading())
//        val response = notesAPI.createNote(noteRequest)
//        handleResponse(response, "Note Created")
//
//    }

    private fun handleResponse(response: Response<NoteResponse>, message : String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(NetworkResult.Success(message))
        } else {
            _statusLiveData.postValue(NetworkResult.Error("Something went Wrong!!"))
        }
    }
//
//    suspend fun deleteNote(noteId: String){
//        _statusLiveData.postValue(NetworkResult.Loading())
//        val response = notesAPI.deleteNote(noteId)
//        handleResponse(response, "Note Deleted")
//    }
//    suspend fun updateNote(noteId : String ,noteRequest: NoteRequest){
//        _statusLiveData.postValue(NetworkResult.Loading())
//        val response = notesAPI.updateNote(noteId, noteRequest)
//        handleResponse(response, "Note Updated")
//    }
}