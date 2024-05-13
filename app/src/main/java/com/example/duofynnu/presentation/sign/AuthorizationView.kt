package com.example.duofynnu.presentation.sign

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.duofynnu.databinding.ActivityAuthorizationViewBinding

class AuthorizationView : AppCompatActivity() {
    private lateinit var authorizationViewBinding: ActivityAuthorizationViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        authorizationViewBinding=ActivityAuthorizationViewBinding.inflate(layoutInflater)
        setContentView(authorizationViewBinding.root)
    }
}