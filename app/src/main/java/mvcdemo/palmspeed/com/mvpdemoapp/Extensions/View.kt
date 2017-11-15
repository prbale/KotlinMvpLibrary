package mvcdemo.palmspeed.com.mvpdemoapp.Extensions

import android.content.Context
import android.content.res.Resources
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.view.View
import mvcdemo.palmspeed.com.mvpdemoapp.R

/**
 * Created by Prashant on 11-11-2017.
 */

fun View.pxToDp(px: Float): Float {
  val densityDpi = Resources.getSystem().displayMetrics.densityDpi.toFloat()
  return px / (densityDpi / 160f)
}

fun View.dpToPx(dp: Float): Int {
  val density = Resources.getSystem().displayMetrics.density
  return Math.round(dp * density)
}

fun View.changeIconDrawableToGray(context: Context, drawable: Drawable?) {
  if (drawable != null) {
    drawable.mutate()
    drawable.setColorFilter(ContextCompat
        .getColor(context, R.color.dark_gray), PorterDuff.Mode.SRC_ATOP)
  }
}

fun View.showSnackBarLong(text: CharSequence) = Snackbar.make(this, text, Snackbar.LENGTH_LONG).show()