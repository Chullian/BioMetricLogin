package com.chullian.afstrial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DetailActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        findViewById<Button>(R.id.button).setOnClickListener{
            startActivity(Intent(this,ThirdActivity::class.java))
        }
    }
    override fun onResume() {
        if(!App.wasInForeground){
            val intent = Intent(this, MainActivity::class.java)
            intent.action = INTENT_ACTION_RE_LOGIN
            startActivity(intent)
        }
        super.onResume()
    }

}