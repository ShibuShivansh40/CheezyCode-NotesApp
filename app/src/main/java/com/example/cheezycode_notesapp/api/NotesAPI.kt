package com.example.cheezycode_notesapp.api

import android.provider.ContactsContract.CommonDataKinds.Note
import com.example.cheezycode_notesapp.models.NoteRequest
import com.example.cheezycode_notesapp.models.NoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface NotesAPI {

    @POST("")
    suspend fun getNotes() : Response<List<NoteResponse>>
//
//    @POST("")
//    suspend fun createNote(@Body noteRequest : NoteRequest): Response<NoteResponse>
//    //Not Required
//
//    @PUT("/note/{noteId}")
//    // To make the NoteId Dynamic just add '/{noteId}' in the end and add  @Path in the Argument Section of the Suspended Function
//    suspend fun updateNote(@Path("noteId") noteId : String, @Body noteRequest: NoteRequest) : Response<NoteResponse>
//    //note required
//
//    @DELETE("/note/{noteId}")
//    suspend fun deleteNote(@Path("noteId") noteId:String) : Response<NoteResponse>
    //Not required

}