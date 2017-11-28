package mvclib.palmspeed.com.mvplibrary

import android.support.annotation.UiThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

import java.lang.ref.WeakReference

/**
 * A base implementation of a [MvpPresenter] that uses a **WeakReference** for referring to
 * the attached view


 * @param <V> type of the [MvpView]
 * *
 * @author Prashant Bale
</V> */

open class MvpBasePresenter<V : MvpView> : MvpPresenter<V> {

  /**
   * This will hold weak reference of the view. So that there is no memory leak if in case of orientation
   * change or any other situations.
   */
  private var weakViewRef: WeakReference<V>? = null

  private val disposables = CompositeDisposable()

  @UiThread
  override fun attachView(view: V) {
    weakViewRef = WeakReference(view)
    println("Attaching view : " + weakViewRef)
  }

  @UiThread
  override fun detachView(retainInstance: Boolean) {

    if (weakViewRef != null) {
      weakViewRef?.clear()
      weakViewRef = null  // Now weakViewRef available for Garbage collection.
    }

    println("View detached : " + weakViewRef)
  }

  /**
   * Get the attached view. You should always call [.isViewAttached] to check if the view
   * is attached to avoid NullPointerException.

   * @return `null`, if view is not attached, otherwise the concrete view instance.
   */
  val view: V?
    get() = if (weakViewRef == null) null else weakViewRef?.get()


  /**
   * Check if view is attached to this presenter. You should always call this method before
   * calling [.getView] to get the view instance.
   */
  val isViewAttached: Boolean
    get() = weakViewRef != null && weakViewRef!!.get() != null

  /**
   * Contains common setup actions needed for all presenters, if any.
   * Subclasses may override this.
   */
  override fun start() {
  }

  /**
   * Contains common cleanup actions needed for all presenters, if any.
   * Subclasses may override this.
   */
  override fun stop() {
    disposables.clear()
  }

  protected fun addDisposable(disposable: Disposable) {
    disposables.add(disposable)
  }

}
