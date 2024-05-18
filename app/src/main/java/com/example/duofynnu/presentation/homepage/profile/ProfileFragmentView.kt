package com.example.duofynnu.presentation.homepage.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.duofynnu.databinding.FragmentProfileViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragmentView : Fragment() {
    private var _profileViewBinding:FragmentProfileViewBinding?=null
    private val profileViewBinding get() = _profileViewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _profileViewBinding=FragmentProfileViewBinding.inflate(layoutInflater,container,false)
        val view=profileViewBinding.root
        return view
    }

    override fun onDestroy() {
        _profileViewBinding=null
        super.onDestroy()
    }

}