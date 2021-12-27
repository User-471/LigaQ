package com.ligasportquiz.freeforyou.bestplayer

import android.app.Application
import timber.log.Timber

class LigaQApp: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}