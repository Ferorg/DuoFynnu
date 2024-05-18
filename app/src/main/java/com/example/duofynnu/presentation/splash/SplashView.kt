package com.example.duofynnu.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.duofynnu.R
import com.example.duofynnu.presentation.homepage.HomePageActivity
import com.example.duofynnu.presentation.sign.AuthorizationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashView : AppCompatActivity(R.layout.activity_splash_view) {
    private val viewModel: SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeSplashState()
    }

    private fun observeSplashState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.viewState.collect {
                    when (it) {
                        SplashViewState.Success -> {
                            startActivity(Intent(this@SplashView, HomePageActivity::class.java))
                            finish()
                        }

                        SplashViewState.Failure -> {
                            startActivity(Intent(this@SplashView, AuthorizationView::class.java))
                            finish()
                        }

                        SplashViewState.Loading -> {}
                    }
                }
            }
        }
    }
}