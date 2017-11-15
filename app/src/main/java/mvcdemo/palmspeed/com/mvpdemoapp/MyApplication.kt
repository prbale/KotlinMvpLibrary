package mvcdemo.palmspeed.com.mvpdemoapp

import android.app.Application

class MyApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    instance = this
  }

  companion object {
    var instance: MyApplication? = null
      private set
  }
}