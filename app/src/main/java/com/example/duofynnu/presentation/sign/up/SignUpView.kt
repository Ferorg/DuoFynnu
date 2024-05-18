package com.example.duofynnu.presentation.sign.up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.duofynnu.R
import com.example.duofynnu.databinding.FragmentSignUpViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpView : Fragment() {
    private var _fragmentSignUpViewBinding: FragmentSignUpViewBinding? = null
    private val fragmentSignUpViewBinding = _fragmentSignUpViewBinding!!
    private lateinit var emailEditView: EditText
    private lateinit var passwordEditView: EditText
    private lateinit var repeatPasswordEditView: EditText
    private lateinit var enterNameEditView: EditText
    private lateinit var signUpButton: Button
    private lateinit var signInButtonView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentSignUpViewBinding =
            FragmentSignUpViewBinding.inflate(layoutInflater, container, false)
        val view = fragmentSignUpViewBinding.root
        initView()
        return view
    }

    private fun initView() {
        emailEditView = fragmentSignUpViewBinding.emailEditView
        passwordEditView = fragmentSignUpViewBinding.passwordEditView
        repeatPasswordEditView = fragmentSignUpViewBinding.repeatPasswordEditView
        enterNameEditView = fragmentSignUpViewBinding.enterNameEditView
        signUpButton = fragmentSignUpViewBinding.signUpButton
        signInButtonView = fragmentSignUpViewBinding.signInButtonView
    }


    override fun onDestroy() {
        _fragmentSignUpViewBinding = null
        super.onDestroy()
    }
}