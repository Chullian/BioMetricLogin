package com.chullian.afstrial

import android.app.Activity
import android.app.Application
import android.os.Bundle


class App :Application() {
    companion object{
        var wasInForeground:Boolean = false
    }
    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(AppLifecycleTracker{
           if(!it) wasInForeground = false
        })
    }

}

class AppLifecycleTracker(val isAppForeground :(Boolean)->Unit) : Application.ActivityLifecycleCallbacks  {

    private var numStarted = 0

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {

    }

    override fun onActivityStarted(activity: Activity) {
        if (numStarted == 0) {
            isAppForeground(true)
        }
        numStarted++
    }

    override fun onActivityResumed(activity: Activity) {

    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {
        numStarted--
        if (numStarted == 0) {
            isAppForeground(false)
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {

    }

}