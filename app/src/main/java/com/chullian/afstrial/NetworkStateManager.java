package com.chullian.afstrial;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NetworkStateManager {
    private static NetworkStateManager instance;
    private static final MutableLiveData<Boolean> _activeNetworkStatus = new MutableLiveData<>();
    private NetworkStateManager() {}

    public static synchronized NetworkStateManager getInstance() {
        if (instance == null) {
            instance = new NetworkStateManager();
        }
        return instance;
    }

    /**
     * Updates the active network status live-data
     */
    public void setNetworkConnectivityStatus(boolean connectivityStatus) {
        _activeNetworkStatus.postValue(connectivityStatus);
    }

    /**
     * Returns the current network status
     */
    public LiveData<Boolean> getNetworkConnectivityStatus() {
        return _activeNetworkStatus;
    }
}