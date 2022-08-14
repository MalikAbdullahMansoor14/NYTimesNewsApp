package com.example.programmingtest.ui.splash
/**
 * @author Abdullah Mansoor
 * @Date 8/13/22
 *
 * This is the launcher activity where user can see the logo of the app
 */


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.programmingtest.R
import com.example.programmingtest.ui.MainActivity
import kotlinx.coroutines.delay

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launchWhenCreated {
            delay(1000)
            navigateToMain()
        }

    }

    private fun navigateToMain() {
        startActivity(MainActivity.newsActivity(this@SplashActivity))
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        finish()
    }

}