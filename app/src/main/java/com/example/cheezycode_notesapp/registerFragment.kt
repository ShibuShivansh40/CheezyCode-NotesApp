package com.example.cheezycode_notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.cheezycode_notesapp.databinding.FragmentRegisterBinding
import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.utils.NetworkResult
import com.example.cheezycode_notesapp.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import java.net.PasswordAuthentication
import javax.inject.Inject

@AndroidEntryPoint // Here we have defined our android entry point
class registerFragment : Fragment() {

    private var _binding : FragmentRegisterBinding? = null
    private val binding get() = _binding!! // This is used to NullSafe the Binding Object.
    private val authViewModel by viewModels<AuthViewModel>() // Object for ViewModel using Kotlin extensions

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
//        //Now here we can access all our views through this null safe variable
        if(tokenManager.getToken() !=null){//Here we have checked that we have a token or not. If we have a token then we will navigate to the MainFragment
            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
        }

        return binding.root
        //Here we are returning to the view of the Register Fragment
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)

         binding.btnLoginText.setOnClickListener{
//             Toast.makeText(context, "Unable to Open the Login Page" , Toast.LENGTH_LONG).show()
             it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
         }

         binding.btnSignUp.setOnClickListener{
             val validationResult = validateUserInput()
             if(validationResult.first){
                 authViewModel.registerUser(getUserRequest())
             }
             else{
                 binding.txtError.text = validationResult.second
             }
         }
         bindObservers()
     }

    private fun getUserRequest(): UserRequest{
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        val username = binding.txtUsername.text.toString()
        return UserRequest(emailAddress,username,password)
    }
    private fun validateUserInput(): Pair<Boolean, String>{
        val userRequest = getUserRequest()
        return authViewModel.validateCredentials(userRequest.email, userRequest.name,  userRequest.password, false)
    }
     private fun bindObservers() {
         authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer{
             binding.progressBar.isVisible = false
             when (it){
                 is NetworkResult.Success -> {
                     tokenManager.saveToken(it.data!!.token)//It basically saves the token into the local storage
                     findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                 }
                 is NetworkResult.Error -> {
                     binding.txtError.text = it.message
                 }
                 is NetworkResult.Loading -> {
                     binding.progressBar.isVisible=true
                 }
             }
         })
         // Here we are assigning everything a task through the conditions

     }

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