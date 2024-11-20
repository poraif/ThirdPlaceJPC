package ie.por.thirdplace2.main

import android.app.Application
import timber.log.Timber

class ThirdPlaceMainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        Timber.i("Starting Third Place...")
    }

}