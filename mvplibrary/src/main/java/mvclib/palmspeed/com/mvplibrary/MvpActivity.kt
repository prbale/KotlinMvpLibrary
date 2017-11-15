package mvclib.palmspeed.com.mvplibrary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * An Activity that uses a [MvpPresenter] to implement a Model-View-Presenter
 * architecture.
 * Any screen extends this MvpActivity, It has to provide MvpView and MvpPresenter. That's mandatory !

 * @author Prashant Bale
 */
abstract class MvpActivity<V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(), MvpView {

  protected lateinit var presenter: P
  protected lateinit var view: V

  /**
   * Instantiate a presenter instance
   * @return The [MvpPresenter] for this view
   */
  abstract fun createPresenter(): P

  /**
   * Instantiate a View instance
   * @return The [MvpView]
   */
  abstract fun getViewRef(): V

  protected abstract fun tag(): String

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    presenter = createPresenter()
    view = getViewRef()
    presenter.attachView(view)
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView(true)
  }
}
