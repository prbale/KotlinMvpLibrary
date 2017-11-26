package mvcdemo.palmspeed.com.mvpdemoapp.Base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import mvcdemo.palmspeed.com.mvpdemoapp.Extensions.showLoadingDialog
import mvcdemo.palmspeed.com.mvpdemoapp.R
import mvclib.palmspeed.com.mvplibrary.MvpActivity
import mvclib.palmspeed.com.mvplibrary.MvpFragment
import mvclib.palmspeed.com.mvplibrary.MvpPresenter
import mvclib.palmspeed.com.mvplibrary.MvpView

abstract class BaseActivity<V : MvpView, P : MvpPresenter<V>> : MvpActivity<V, P>(), MvpView, MvpFragment.Callback {

  private var mProgressDialog: ProgressDialog? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      requestPermissions(permissions, requestCode)
    }
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun hasPermission(permission: String): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(
        permission) == PackageManager.PERMISSION_GRANTED
  }

  override fun showLoading() {
    hideLoading()
    mProgressDialog = showLoadingDialog()
  }

  override fun hideLoading() {
    if (mProgressDialog != null && (mProgressDialog?.isShowing as Boolean)) {
        mProgressDialog?.cancel()
    }
  }

  private fun showSnackBar(message: String) {
    // Code to display snack bar
    println("Message : " + message)
  }

  override fun onError(message: String) {
    if (!message.isEmpty() && !message.isBlank()) {
      showSnackBar(message)
    } else {
      showSnackBar(getString(R.string.some_error))
    }
  }

  override fun showMessage(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
  }

  override fun onFragmentAttached() {
  }

  override fun onFragmentDetached(tag: String) {
  }

}
