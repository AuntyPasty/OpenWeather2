package daveho.co.auntypasty.openweather2

import android.app.Application
import android.content.Context


/**
 * Allows the application Context to be retrieved from other areas of code.
 */
object ApplicationModule {

    private lateinit var mAppContext: Context
    private lateinit var mApplication: Application

    fun init(app: Application) {
        mApplication = app
        mAppContext = app.applicationContext
    }

    fun application(): Application? {
        return mApplication
    }

    fun applicationContext(): Context {
        return mAppContext
    }
}
