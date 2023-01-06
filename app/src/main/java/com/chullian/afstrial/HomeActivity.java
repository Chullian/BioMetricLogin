package com.chullian.afstrial;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findViewById(R.id.button).setOnClickListener(v -> {
            boolean isNetworkAvailable = Boolean.TRUE.equals(NetworkStateManager.getInstance().getNetworkConnectivityStatus().getValue());
            if(!isNetworkAvailable)
                NetworkStateManager.getInstance().setNetworkConnectivityStatus(false);
            else startActivity(new Intent(this, JavaDetailActivity.class));
        });
    }


}