package com.chullian.afstrial;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.function.Function;

public class MyApp extends Application {

    static boolean wasInForeground = false;

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new LifeCycleTracker(new LifeCycleCallback() {
            @Override
            public void isAppForeground(Boolean isForeground) {
                if(!isForeground) wasInForeground = false;
            }
        }));
    }

    interface LifeCycleCallback{
        void isAppForeground(Boolean isForeground);
    }

    class LifeCycleTracker implements Application.ActivityLifecycleCallbacks{
        LifeCycleCallback callback;

        LifeCycleTracker(LifeCycleCallback callback){
            this.callback = callback;
        }

        private int numStarted = 0;
        @Override
        public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

        }

        @Override
        public void onActivityStarted(@NonNull Activity activity) {
            if (numStarted == 0) {
                callback.isAppForeground(true);
            }
            numStarted++;
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

        }

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {
            numStarted--;
            if (numStarted == 0) {
                callback.isAppForeground(false);
            }
        }

        @Override
        public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

        }
    }
}


