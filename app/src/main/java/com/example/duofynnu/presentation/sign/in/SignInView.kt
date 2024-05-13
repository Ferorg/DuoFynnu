package com.example.duofynnu.presentation.sign.`in`

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.duofynnu.R
import com.example.duofynnu.databinding.FragmentSignInViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInView : Fragment() {
    private var _signInViewBinding:FragmentSignInViewBinding?=null
    private val signInViewBinding get() = _signInViewBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _signInViewBinding=FragmentSignInViewBinding.inflate(layoutInflater,container,false)
        val view =signInViewBinding.root
        return view
    }

    override fun onDestroy() {
        _signInViewBinding=null
        super.onDestroy()
    }

}