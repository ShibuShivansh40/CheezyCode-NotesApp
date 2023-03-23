package com.example.cheezycode_notesapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheezycode_notesapp.models.User
import com.example.cheezycode_notesapp.models.UserLogin
import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.models.UserResponse
import com.example.cheezycode_notesapp.repository.UserRepository
import com.example.cheezycode_notesapp.utils.NetworkResult
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// This is the class for ViewModel

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository) :ViewModel(){

    val userResponseLiveData : LiveData<NetworkResult<UserResponse>>
    get() = userRepository.userResponseLiveData

    fun registerUser(userRequest: UserRequest){
        //Here we have launched our Coroutine and called the function from userRepository that is registerUser
        viewModelScope.launch {
            userRepository.registerUser(userRequest)
        }
    }
    //Here we have launched our Coroutine and called the function from userRepository that is loginrUser
    fun loginUser(userLogin: UserLogin){
        viewModelScope.launch {
            userRepository.loginUser(userLogin)
        }
    }


}