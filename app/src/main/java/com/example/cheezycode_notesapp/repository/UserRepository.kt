package com.example.cheezycode_notesapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cheezycode_notesapp.api.UserAPI
import com.example.cheezycode_notesapp.models.UserLogin
import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.models.UserResponse
import com.example.cheezycode_notesapp.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
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
        handleResponse(response)

    }
    suspend fun loginUser(userLogin: UserLogin){
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response = userAPI.signin(userLogin)
        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            Log.e("RESPONSE_CODE" , response.body().toString())
            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
//            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
    //            Here we're parsing the JSON with Array
                _userResponseLiveData.postValue(NetworkResult.Error("SOMETHING WENT WRONG!"))
//            This tells us that we're unable to read the Error Message , so we will be commenting this line of up
//            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
//            Right now, it is creating an error : -     org.json.JSONException: Value Tunnel of type java.lang.String cannot be converted to JSONObject
        } else {
            _userResponseLiveData.postValue(NetworkResult.Error("Something went Wrong!"))
        }
    }

}