package mvclib.palmspeed.com.mvplibrary

import android.support.annotation.UiThread

/**
 * The base interface for each mvp presenter.
 *
 * Assume that all the interaction (i.e. updating the view) between presenter and view is
 * executed on android's main UI thread.
 *
 * @author Prashant Bale.
 */
interface MvpPresenter<in V : MvpView> {

  /**
   * This is the method which every presenter must invoke when view created.
   * Using this we can do initialization stuff for presenter as well as for view.
   */
  @UiThread
  fun start()

  /**
   * This will have two method declaration which will need to implement in our presenter classes.
   * These methods helps to attach and detach view on orientation change
   * Which helps to maintain the memory.
   */
  @UiThread
  fun attachView(view: V)

  /**
   * Will be called if the view has been destroyed. Typically this method will be invoked from
   * `Activity.detachView()` or `Fragment.onDestroyView()`
   * @param retainInstance
   */
  @UiThread
  fun detachView(retainInstance: Boolean)

  fun stop()
}
