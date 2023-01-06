package com.chullian.afstrial;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

public class NetworkCheckingUtil extends ConnectivityManager.NetworkCallback {
    private final NetworkRequest mNetworkRequest;
    private final ConnectivityManager mConnectivityManager;

    public NetworkCheckingUtil(Context context) {
        mNetworkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }



    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        NetworkStateManager.getInstance().setNetworkConnectivityStatus(true);
    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        NetworkStateManager.getInstance().setNetworkConnectivityStatus(false);
    }

    public void registerNetworkCallbackEvents() {
        mConnectivityManager.registerNetworkCallback(mNetworkRequest, this);
    }

    public void checkNetwork(){
        try {
            NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
            // Set the initial value for the live-data
            NetworkStateManager.getInstance().setNetworkConnectivityStatus(networkInfo != null&& networkInfo.isConnected());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
