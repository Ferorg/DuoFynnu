package com.example.duofynnu.presentation.sign.up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.duofynnu.R
import com.example.duofynnu.databinding.FragmentSignUpViewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpView : Fragment() {
    private var _signUpViewBinding: FragmentSignUpViewBinding? = null
    private val signUpViewBinding get() = _signUpViewBinding!!
    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var signUpEmailEditView: EditText
    private lateinit var signUpPasswordEditView: EditText
    private lateinit var repeatPasswordEditView: EditText
    private lateinit var enterNameEditView: EditText
    private lateinit var signUpButton: Button
    private lateinit var signInButtonView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _signUpViewBinding = FragmentSignUpViewBinding.inflate(inflater, container, false)
        val view = signUpViewBinding.root
        initView()
        onClickSignUp()
        onClickSignIn()
 //       observeEvents()
        return view
    }

    private fun initView() {
        signUpEmailEditView = signUpViewBinding.signUpEmailEditView
        signUpPasswordEditView = signUpViewBinding.signUpPasswordEditView
        repeatPasswordEditView = signUpViewBinding.repeatPasswordEditView
        enterNameEditView = signUpViewBinding.enterNameEditView
        signUpButton = signUpViewBinding.signUpButton
        signInButtonView = signUpViewBinding.signInButtonView
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.viewState.collect {
                    when (it) {
                        is SignUpViewState.Success -> {
                            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                                //findNavController().navigate(R.id.action_to_sign_in_fragment)
                        }

                        is SignUpViewState.Loading -> {
//                            signUpViewBinding.signUpButton.isEnabled = false
                            Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                        }

                        is SignUpViewState.Failure -> {
//                            signUpViewBinding.signUpButton.isEnabled = true
                        }

                        is SignUpViewState.Idle -> {}
                    }
                }
            }
        }
    }

    private fun onClickSignUp() {
        signUpButton.setOnClickListener {
            if ((signUpPasswordEditView.text.toString()) == repeatPasswordEditView.text.toString()) {
                signUpViewModel.onSignUpButtonClicked(
                    email = signUpEmailEditView.text.toString(),
                    password = signUpPasswordEditView.text.toString(),
                    username = enterNameEditView.text.toString()
                )
            }

        }
    }

    private fun onClickSignIn() {
        signInButtonView.setOnClickListener {
            findNavController().navigate(R.id.action_to_sign_in_fragment)
        }
    }

    override fun onDestroy() {
        _signUpViewBinding = null
        super.onDestroy()
    }


}