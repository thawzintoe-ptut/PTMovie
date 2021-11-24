package com.ptut.ptmovie.feature.splash

import android.content.Intent
import android.os.Bundle
import com.ptut.appbase.core.mvp.MvpActivity
import com.ptut.ptmovie.databinding.ActivitySplashBinding
import com.ptut.ptmovie.feature.MainActivity

class SplashActivity : MvpActivity<ActivitySplashBinding, SplashView, SplashViewModel>(), SplashView {
    override val viewModel: SplashViewModel by contractedViewModel()
    override val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        binding.btnSplashContinue.setOnClickListener {
            startActivity(
                Intent(this, MainActivity::class.java)
            )
            finish()
        }
    }

    override fun subscribeView() {
    }
}
