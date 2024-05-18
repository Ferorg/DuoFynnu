package com.example.duofynnu.presentation.homepage.rooms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.duofynnu.R
import com.example.duofynnu.databinding.FragmentRoomsViewBinding
import com.example.duofynnu.domain.entity.Room
import com.example.duofynnu.presentation.homepage.rooms.adapter.RoomsAdapter
import com.example.duofynnu.presentation.util.ViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RoomsView : Fragment() {

    private var _fragmentRoomsViewBinding:FragmentRoomsViewBinding?=null
    private val fragmentRoomsViewBinding get() = _fragmentRoomsViewBinding!!
    private val viewModel:RoomsViewModel by viewModels()
    private lateinit var adapter: RoomsAdapter
    private lateinit var roomsView:RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _fragmentRoomsViewBinding=FragmentRoomsViewBinding.inflate(inflater,container,false)
        val view=fragmentRoomsViewBinding.root
        initRoomsRV()
        return view
    }

    private fun initRoomsRV() {
        roomsView=fragmentRoomsViewBinding.roomsContainer
        roomsView.layoutManager=GridLayoutManager(context,2)
        adapter= RoomsAdapter(
            mutableListOf()
        ){room->
            actionToRoomHomePage(room)

        }

    }

    private fun actionToRoomHomePage(room: Room) {

    }
    private fun observeRooms() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    when (it) {
                        is ViewState.Success -> {
                            handleOnSuccess(it.data)
                        }

                        is ViewState.Loading -> {
                            roomsView.visibility = View.GONE
                        }

                        is ViewState.Failure -> {
                            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun handleOnSuccess(rooms: List<Room>) {
        adapter.setRooms(rooms)
        roomsView.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        _fragmentRoomsViewBinding = null
        super.onDestroy()

    }
}