package com.example.cheezycode_notesapp.repository

import android.net.Network
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cheezycode_notesapp.api.UserAPI
import com.example.cheezycode_notesapp.models.UserLogin
import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.models.UserResponse
import com.example.cheezycode_notesapp.utils.Constants.TAG
import com.example.cheezycode_notesapp.utils.NetworkResult
import javax.inject.Inject

//This is the Repository Class

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
//Data will be stored in the above variable
    val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
    get() = _userResponseLiveData
    // Data can be accessed from here


    suspend fun registerUser(userRequest: UserRequest){
        // Here we have implemented the Loading State
        _userResponseLiveData.postValue(NetworkResult.Loading())

        //Here a sign up request through the API
        val response = userAPI.signup(userRequest)
        //Log.d(TAG , response.body().toString()) To check if my app is working fine

        //To check if response is an error or a genuine response.

        if(response.isSuccessful && response.body() != null){
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if (response.errorBody()!=null){
            _userResponseLiveData.postValue(NetworkResult.Error("SOMETHING WENT WRONG!"))
        }
        else{
            _userResponseLiveData.postValue(NetworkResult.Error("Something went Wrong!"))
        }

    }

    suspend fun loginUser(userLogin: UserLogin){
        val response = userAPI.signin(userLogin)
        Log.d(TAG , response.body().toString())
    }
}