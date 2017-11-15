package mvclib.palmspeed.com.mvplibrary

import android.app.Fragment
import android.os.Bundle
import android.view.View

/**
 * A Fragment that uses a [MvpPresenter] to implement a Model-View-Presenter
 * architecture.

 * @author Prashant Bale
 */
abstract class MvpFragment<V : MvpView, P : MvpPresenter<V>> : Fragment(), MvpView {

  protected lateinit var presenter: P
  protected lateinit var view: V

  /**
   * Instantiate a presenter instances
   * @return The [MvpPresenter] for this view
   */
  abstract fun createPresenter(): P

  /**
   * Instantiate a View instance
   * @return The [MvpView]
   */
  abstract fun getFragmentView(): V

  override fun onViewCreated(v: View?, savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    presenter = createPresenter()
    view = getFragmentView()
    presenter.attachView(view)
  }

  override fun onDestroyView() {
    super.onDestroyView()
    presenter.detachView(retainInstance)
  }

  interface Callback {
    fun onFragmentAttached()
    fun onFragmentDetached(tag: String)
  }
}
