package com.example.duofynnu.presentation.homepage

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.example.duofynnu.R
import com.example.duofynnu.databinding.ActivityHomePageBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : AppCompatActivity() {
    private lateinit var homePageBinding: ActivityHomePageBinding
    private lateinit var addButton:FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePageBinding=ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(homePageBinding.root)
        initView()
        initMenu()
    }
    private fun initView() {
        addButton=homePageBinding.addRoomBtnView
    }
    private fun initMenu() {
       val menuItem=homePageBinding.bottomNavigationView
        menuItem.setOnItemSelectedListener {
            choiceItemMenu(it.itemId)
            true
        }
        addButton.setOnClickListener {
            clickAddButton()
        }
    }

    private fun choiceItemMenu(itemId: Int) {
        when (itemId) {
            R.id.menuProfile -> {
                findNavController(R.id.nav_host_home_fragment).navigate(
                    R.id.action_to_profile_view
                )
            }

            R.id.menuListRooms -> {
                findNavController(R.id.nav_host_home_fragment).navigate(
                    R.id.action_to_rooms_fragment
                )
            }

            R.id.menuExit -> {
                findNavController(R.id.nav_host_home_fragment).navigate(R.id.exit_to_sign_in_view)
            }
        }
    }


    private fun clickAddButton() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Room")
            .setMessage("Do you want to connect or create a new room?")
            .setPositiveButton("Connect") { dialog, which ->
                findNavController(R.id.nav_host_home_fragment).navigate(R.id.action_to_connecting_room_fragment)
            }
            .setNegativeButton("Create") { dialog, which ->
                findNavController(R.id.nav_host_home_fragment).navigate(R.id.action_to_add_room_fragment)
            }
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}