package com.chullian.afstrial

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {



    private val authentication: BioAuthentication by lazy {
        BioAuthentication(this) {
            if (it) {
                App.wasInForeground = true
                if(intent.action==INTENT_ACTION_RE_LOGIN) finish()
                else startActivity(Intent(this, DetailActivity::class.java))
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_main)
        val biometricLoginButton =
            findViewById<Button>(R.id.biometric_login)
        biometricLoginButton.setOnClickListener {
            authentication.biometricPrompt.authenticate(authentication.promptInfo)
        }

    }
}