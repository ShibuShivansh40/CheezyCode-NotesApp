package com.example.cheezycode_notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
//import com.example.cheezycode_notesapp.JWTManagementGPT.ApiServiceGPT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.security.auth.callback.Callback

@AndroidEntryPoint // Here we have defined our android entry point
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}