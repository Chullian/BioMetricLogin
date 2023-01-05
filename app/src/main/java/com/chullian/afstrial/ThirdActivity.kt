package com.chullian.afstrial

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)
    }

    override fun onResume() {
        if (!App.wasInForeground) {
            val intent = Intent(this, MainActivity::class.java)
            intent.action = INTENT_ACTION_RE_LOGIN
            startActivity(intent)
        }
        super.onResume()
    }

}