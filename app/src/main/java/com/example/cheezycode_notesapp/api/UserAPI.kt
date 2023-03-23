package com.example.cheezycode_notesapp.api

import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.models.UserResponse
import retrofit2.Response
import retrofit2.http.POST

interface UserAPI {

    //@POST("/users/signin") //Just used to check if the ap[p is working or not
    @POST("/login/")
    fun signin(userRequest: UserRequest) : Response<UserResponse>

    //@POST("/users/signup") //Just used to check if the ap[p is working or not
    @POST("/userRegister/")
    fun signup(userRequest: UserRequest) : Response<UserResponse>
}