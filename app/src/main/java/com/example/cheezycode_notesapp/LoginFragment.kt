package com.example.cheezycode_notesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cheezycode_notesapp.databinding.FragmentLoginBinding
import com.example.cheezycode_notesapp.databinding.FragmentRegisterBinding
import com.example.cheezycode_notesapp.models.UserLogin
import com.example.cheezycode_notesapp.models.UserRequest
import com.example.cheezycode_notesapp.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!! // This is used to NullSafe the Binding Object.
    private val authViewModel by viewModels<AuthViewModel>() // Object for ViewModel using Kotlin extensions
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener{
            val validationResult = validateUserInput()
            if(validationResult.first){
                authViewModel.loginUser(getUserRequest())
            }
            else{
                binding.txtError.text = validationResult.second
            }
        }
        binding.btnSignUp.setOnClickListener{
            findNavController().popBackStack()
        }
        bindObservers()
    }

    private fun getUserRequest(): UserLogin {
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        return UserLogin(emailAddress,password)
    }
    private fun validateUserInput(): Pair<Boolean, String>{
        val userRequest = getUserRequest()
        return authViewModel.validateCredentials(userRequest.email, "",  userRequest.password, true)
    }
    private fun bindObservers() {
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner, Observer{
            binding.progressBar.isVisible = false
            when (it){
                is NetworkResult.Success -> {
                    //Here we also need to add the Token
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
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
}