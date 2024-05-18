package com.example.duofynnu.presentation.sign.`in`

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.duofynnu.R
import com.example.duofynnu.databinding.FragmentSignInViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInView : Fragment() {
    private var _signInViewBinding: FragmentSignInViewBinding? = null
    private val signInViewBinding get() = _signInViewBinding!!
    private val viewModel:SignViewModel by viewModels()
    private lateinit var emailEditView: EditText
    private lateinit var passwordEditView: EditText
    private lateinit var signInButton:Button
    private lateinit var signUpTextButton:TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _signInViewBinding = FragmentSignInViewBinding.inflate(layoutInflater, container, false)
        val view = signInViewBinding.root
        initView()
        setUpViews()
        observeEvents()
        return view
    }
    private fun initView(){
        emailEditView=signInViewBinding.emailEditView
        passwordEditView=signInViewBinding.passwordEditView
        signInButton=signInViewBinding.signInButton
        signUpTextButton=signInViewBinding.registerButtonView
    }

    private fun setUpViews() {
        signInButton.setOnClickListener {
            viewModel.onSignInButtonClicked(
                email = emailEditView.text.toString(),
                password = passwordEditView.text.toString()
            )
        }
        signUpTextButton.setOnClickListener{
            onClickSignUp()
        }
    }
    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    when (it) {
                        is SignInViewState.Success -> {
                            onClickSignIn()
                        }

                        is SignInViewState.Loading -> {
                            signInViewBinding.signInButton.isEnabled = false
                        }

                        is SignInViewState.Failure -> {
                            Log.d("mylog", "SignInViewState.Failure ")
                        }
                        is SignInViewState.Idle -> {}
                    }
                }
            }
        }
    }

    private fun onClickSignIn() {
        findNavController().navigate(R.id.action_to_home_page)
    }

    override fun onDestroy() {
        _signInViewBinding = null
        super.onDestroy()
    }

    private fun onClickSignUp() {  findNavController().navigate(R.id.action_to_register)}

}