package com.example.duofynnu.presentation.homepage.profile

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.checkSelfPermission
import com.example.duofynnu.databinding.FragmentProfileViewBinding
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity

@AndroidEntryPoint
class ProfileFragmentView : Fragment() {
    private var _profileViewBinding: FragmentProfileViewBinding? = null
    private val profileViewBinding get() = _profileViewBinding!!
    private lateinit var profileImgView: ImageView
    private lateinit var nameProfileTextView: TextView
    private lateinit var coinProfileTextView: TextView
    private lateinit var editNameView: EditText
    private lateinit var saveNameButtonView: Button
    private lateinit var editPasswordView: EditText
    private lateinit var savePasswordButtonView: Button


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _profileViewBinding = FragmentProfileViewBinding.inflate(layoutInflater, container, false)
        val view = profileViewBinding.root
        initView()
        onClicks()
        return view
    }

    private fun initView() {
        profileImgView = profileViewBinding.profileImgView
        nameProfileTextView = profileViewBinding.nameProfileTextView
        coinProfileTextView = profileViewBinding.coinProfileTextView
        editNameView = profileViewBinding.editNameView
        saveNameButtonView = profileViewBinding.saveNameButton
        editPasswordView = profileViewBinding.editPasswordView
        savePasswordButtonView = profileViewBinding.savePasswordButton
    }

    private fun onClicks() {
        saveNameButtonView.setOnClickListener {
            Toast.makeText(context, "Click Save Name", Toast.LENGTH_SHORT).show()
        }
        savePasswordButtonView.setOnClickListener {
            Toast.makeText(context, "Click Save Password", Toast.LENGTH_SHORT).show()
        }
        profileImgView.setOnClickListener {
            // Проверяем, есть ли разрешение на использование камеры
            if (checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Если разрешение не предоставлено, запрашиваем его
                requestCameraPermission()
            } else {
                // Если разрешение уже предоставлено, запускаем камеру
                openCamera()
            }
    }}

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }
    private fun requestCameraPermission() {
        requestPermissions(
            arrayOf(Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            CAMERA_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Разрешение предоставлено, запускаем камеру
                    openCamera()
                } else {
                    Toast.makeText(context, "Need Permission on Camera", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
                // Игнорируем все остальные запросы разрешений
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    val bitmap = data?.extras?.get("data") as Bitmap
                    profileImgView.setImageBitmap(bitmap)
                }
            }

            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroy() {
        _profileViewBinding = null
        super.onDestroy()
    }
    companion object {
        const val CAMERA_PERMISSION_REQUEST_CODE = 1001
        const val CAMERA_REQUEST_CODE = 1002
    }


}
