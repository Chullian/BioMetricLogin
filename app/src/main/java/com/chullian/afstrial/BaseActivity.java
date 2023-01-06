package com.chullian.afstrial;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;

public class BaseActivity extends AppCompatActivity {
    private AlertDialog dialog;
    private boolean lastKnownNetworkStatus;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog dialog = new AlertDialog.Builder(this).create();
        NetworkStateManager.getInstance().getNetworkConnectivityStatus().observe(this, isNetworkAvailable -> {
            if(!isNetworkAvailable||lastKnownNetworkStatus) {
                dialog.setTitle("No Network Found");
                dialog.setTitle("Internet disconnected please check your connection, and try again");
                dialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
            else{
                dialog.dismiss();
            }
            lastKnownNetworkStatus = isNetworkAvailable;
        });
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
