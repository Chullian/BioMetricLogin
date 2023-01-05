package com.chullian.afstrial

import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import java.util.concurrent.Executor

class BioAuthentication(val context: FragmentActivity, val authentication: (Boolean) -> Unit) {

    private val executor: Executor = ContextCompat.getMainExecutor(context)
    val biometricPrompt: BiometricPrompt =
        androidx.biometric.BiometricPrompt(context, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    authentication(false)
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    authentication(true)
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    authentication(false)
                }
            })
    var promptInfo: BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Biometric login for my app")
        .setSubtitle("Log in using your biometric credential")
        .setNegativeButtonText("Use account password")
        .build()

}