package com.example.cheezycode_notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cheezycode_notesapp.databinding.FragmentRegisterBinding
import com.example.cheezycode_notesapp.models.UserLogin
import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

 @AndroidEntryPoint // Here we have defined our android entry point
class registerFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!! // This is used to NullSafe the Binding Object.
    private val authViewModel by viewModels<AuthViewModel>() // Object for ViewModel using Kotlin extensions

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        //Now here we can access all our views through this null safe variable
        //binding.textViewRegister
        //This code will shift the fragment from Register Fragment to Login Appp
        binding.btnLoginText.setOnClickListener{
            //This is done to check that we are able to call the viewModel using Fragment
            //This is to check if the correct user is logging in the app, but right now, it's not working. Check it when the server is up.
//            authViewModel.loginUser(UserLogin("hello_cse21@gmail.com", "test0011")) // validation Error : Name is not allowed
            authViewModel.loginUser(UserLogin("_DEFAULT","hello_cse21@gmail.com", "test0011")) // validation Error : Name is not allowed
            //findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        binding.btnSignUp.setOnClickListener{
            //This is done to check that we are able to call the viewModel using Fragment
            // This is to check if the correct user is logging in the app, but right now, it's not working. Check it when the server is up.
            authViewModel.registerUser(UserRequest("Shivansh Seth","hello_cse21@gmail.com", "test0011"))
            //Console Log has been updated.
            //findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }
        return binding.root
        //Here we are returning to the view of the Register Fragment
    }

//     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//         super.onViewCreated(view, savedInstanceState)
//         authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer{
//             binding.progressBar.isVisible = false
//             when (it){
//                 is NetworkResult.Success -> {
//                     //Here we also need to add the Token
//                     findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
//                 }
//                 is NetworkResult.Error -> {
//                     binding.txtError.text = it.message
//                 }
//                 is NetworkResult.Loading -> {
//                     binding.progressBar.isVisible=true
//                 }
//             }
//         })
//         // Here we are assigning everything a task through the conditions
//
//     }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Actual binding object is set to null
    }


    /*
    Create Binding Object
         private var _binding : FragmentRegisterBinding? = null
        private val binding get() = _binding!!

    Initialize binding object in onCreateView
         _binding = FragmentRegisterBinding.inflate(inflater, container, false)

    Override onDestroyView & set binding to null
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
            }
     */
}