package mvclib.palmspeed.com.mvplibrary

/**
 * This is base interface/contract for all the MVP View interface.
 * This interface common methods which will required across all the views.

 * @author Prashant Bale.
 */
interface MvpView {

  /**
   * This method will get called from presenter start() to initialization stuff
   */
  fun init()

  /**
   * This will notify view to display loading view.
   */
  fun showLoading()

  /**
   * This will notify view that background operation has been completed.
   * Loading view can be hide or removed.
   */
  fun hideLoading()

  /**
   * This will notify view, that there has been some error.
   * @param message String to display error message to the user.
   */
  fun onError(message: String)

  /**
   * This will notify view with some specific message.
   * TODO: May be we don't require this.
   * @param message Message string to be displayed.
   */
  fun showMessage(message: String)

}

