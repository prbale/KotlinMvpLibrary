package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import mvclib.palmspeed.com.mvplibrary.MvpPresenter
import mvclib.palmspeed.com.mvplibrary.MvpView
import rx.Observable

interface AddActivityMvpContract {

  interface Presenter : MvpPresenter<View> {
    fun addTwoNumbers(firstNumber: String, secondNumber: String)
    fun setRouter(router: Router)
    fun getView(): View?
  }

  interface View : MvpView {
    fun displayAddition(res: Int)
    fun displayError(errMsg: String)
    fun displayProgress()
    fun dismissProgress()
    fun setRouterToPresenter()
  }

  interface Interactor {
    fun addTwoNumbers(firstNumber: String, secondNumber: String) : Observable<Any>
    interface addActivityMvpInteractorCallback {
      fun calculatedAddition(res: Int)
      fun displayError(errMsg: String)
    }
  }

  interface Router {
    fun goToErrorPage(errorMessage: String)
    fun goToSuccessPage(result: String)
  }
}
