package com.example.duofynnu.presentation.homepage.rooms.connectingroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.duofynnu.R
import com.example.duofynnu.databinding.ContentRoomHomePageBinding

class ConnectingRoomsView : Fragment() {
    lateinit var buttonConnecting:Button
    var _connectingRoomsViewBinding:ContentRoomHomePageBinding?=null
    private val connectingRoomsViewBinding get() = _connectingRoomsViewBinding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _connectingRoomsViewBinding= ContentRoomHomePageBinding.inflate(layoutInflater,container,false)
        val view = connectingRoomsViewBinding.root
        buttonConnecting=connectingRoomsViewBinding.connectingRoomButton
        return view
    }


}