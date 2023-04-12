package com.example.cheezycode_notesapp

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cheezycode_notesapp.models.UserLogin
import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.models.UserResponse
import com.example.cheezycode_notesapp.repository.UserRepository
import com.example.cheezycode_notesapp.utils.NetworkResult
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

    fun validateCredentials(emailAddress : String , username : String, password : String, isLogin : Boolean) : Pair<Boolean, String>{
        var result = Pair(true, "")
        if((!isLogin && TextUtils.isEmpty(username)) || TextUtils.isEmpty(password) || TextUtils.isEmpty(emailAddress)){
            result = Pair(false , "Please provide the credentials")
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            result = Pair(false, "Please enter a Valid Email Address!")
        }
        else if(password.length<=8){
           result = Pair(false, "Password length should be greater than 8")
        }
        return result
    }

}