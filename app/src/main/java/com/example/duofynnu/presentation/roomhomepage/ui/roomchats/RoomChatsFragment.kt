package com.example.duofynnu.presentation.roomhomepage.ui.roomchats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.duofynnu.databinding.FragmentRoomChatsBinding

class RoomChatsFragment : Fragment() {

    private var _binding: FragmentRoomChatsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(RoomChatsViewModel::class.java)

        _binding = FragmentRoomChatsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}