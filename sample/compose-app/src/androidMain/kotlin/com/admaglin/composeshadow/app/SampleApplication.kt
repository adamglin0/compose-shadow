package com.admaglin.composeshadow.app

import android.app.Application
import android.content.Context

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        Thread.setDefaultUncaughtExceptionHandler(GlobalExceptionHandler)
    }

    companion object {
        private lateinit var instance: SampleApplication

        val context: Context
            get() = instance.applicationContext
    }
}

private object GlobalExceptionHandler : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {
        // todo: log the exception
        throw e
    }
}
