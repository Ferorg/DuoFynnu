package com.example.duofynnu.presentation.homepage.rooms.connectingroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.duofynnu.R
import com.example.duofynnu.databinding.FragmentConnectingRoomsViewBinding

class ConnectingRoomsView : Fragment() {
    private lateinit var buttonConnecting: Button
    private var _connectingRoomsViewBinding: FragmentConnectingRoomsViewBinding? = null
    private val connectingRoomsViewBinding get() = _connectingRoomsViewBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _connectingRoomsViewBinding =
            FragmentConnectingRoomsViewBinding.inflate(layoutInflater, container, false)
        val view = connectingRoomsViewBinding.root
        buttonConnecting = connectingRoomsViewBinding.connectingRoomButton
        buttonConnecting.setOnClickListener {
            findNavController().navigate(R.id.action_to_rooms_homepage_fragment)
        }
        return view
    }


}