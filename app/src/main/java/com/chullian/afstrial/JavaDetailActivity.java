package com.chullian.afstrial;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class JavaDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    protected void onResume() {
        if (!MyApp.wasInForeground) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setAction(Constants.INTENT_ACTION_RE_LOGIN);
            startActivity(intent);
        }
        super.onResume();
    }
}