package com.example.duofynnu.presentation.sign.up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.duofynnu.R
import com.example.duofynnu.databinding.FragmentSignUpViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpView : Fragment() {
    private var _signUpViewBinding: FragmentSignUpViewBinding? = null
    private val signUpViewBinding get() = _signUpViewBinding!!
    private val viewModel: SignUpViewModel by viewModels()
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
        _signUpViewBinding = FragmentSignUpViewBinding.inflate(layoutInflater, container, false)
        val view = signUpViewBinding.root
        initView()
        onClick()
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

    private fun onClick() {
        signUpButton.setOnClickListener {
            findNavController().navigate(R.id.action_to_sign_in_fragment)
        }
        signInButtonView.setOnClickListener {
            findNavController().navigate(R.id.action_to_sign_in_fragment)
        }
    }

    override fun onDestroy() {
        _signUpViewBinding = null
        super.onDestroy()
    }


}