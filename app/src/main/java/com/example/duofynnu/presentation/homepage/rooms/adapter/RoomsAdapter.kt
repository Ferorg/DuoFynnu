package com.example.duofynnu.presentation.homepage.rooms.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.duofynnu.R
import com.example.duofynnu.domain.entity.Room


class RoomsAdapter(
    private val rooms: MutableList<Room>,
    private val onItemClickListener: (Room) -> Unit,
) : RecyclerView.Adapter<RoomsAdapter.RoomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_room, parent, false)
        return RoomViewHolder(
            view, onItemClickListener
        )
    }
    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        holder.onBind(rooms[position])
    }

    override fun getItemCount(): Int {
        return rooms.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setRooms(rooms: List<Room>) {
        this.rooms.clear()
        this.rooms.addAll(rooms)
        notifyDataSetChanged()
    }
    class RoomViewHolder(
        private val roomView: View,
        private val onItemClickListener: (Room) -> Unit
    ) : RecyclerView.ViewHolder(roomView) {
        private var nameRoomView: TextView? = null
        init {
            nameRoomView = roomView.findViewById(R.id.roomNameView)
        }
        fun onBind(room: Room) {
            nameRoomView?.text = room.nameRoom
            roomView.setOnClickListener {
                onItemClickListener(room)
            }
        }
    }
}