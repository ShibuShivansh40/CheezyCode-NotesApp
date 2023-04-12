package com.example.cheezycode_notesapp.models

import com.example.cheezycode_notesapp.api.NoteResponse2

// here we are required to frame a response JSON to a function
//data class NoteResponse(
//    val __v: Int,
//    val _id: String,
//    val createdAt : String,
//    val description : String,
//    val title : String,
//    val updatedAt : String,
//    val userId : String
//)

//Basically here we are getting :
/*
{
    "posts" : {
        "tittle" : "Myfirst post",
        "description" : "Random Data"
    }
}
 */

data class NoteResponse(
    val posts : NoteResponse2
)