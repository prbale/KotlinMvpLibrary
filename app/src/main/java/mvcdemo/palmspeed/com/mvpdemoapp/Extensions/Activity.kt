package mvcdemo.palmspeed.com.mvpdemoapp.Extensions

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

/**
 * Created by Prashant on 11-11-2017.
 */

/**
 * The `fragment` is added to the container view with id `frameId`. The operation is
 * performed by the `fragmentManager`.
 */
fun AppCompatActivity.addFragment(
    fragmentManager: FragmentManager,
    fragment: Fragment,
    frameId: Int) {

  kotlin.checkNotNull(fragmentManager)
  kotlin.checkNotNull(fragment)
  val transaction = fragmentManager.beginTransaction()
  transaction.add(frameId, fragment)
  transaction.commit()
}

fun AppCompatActivity.hideKeyboard() {
  val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.hideSoftInputFromWindow(findViewById(android.R.id.content).getWindowToken(), 0);
}

fun AppCompatActivity.setFullScreen() {
  requestWindowFeature(Window.FEATURE_NO_TITLE)
  window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN)

}