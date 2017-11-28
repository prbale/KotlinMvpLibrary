package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import io.reactivex.Observable
import mvclib.palmspeed.com.mvplibrary.MvpPresenter
import mvclib.palmspeed.com.mvplibrary.MvpView

interface AddActivityMvpContract {

  interface View : MvpView {
    fun displayError(errMsg: String)
    fun displayProgress()
    fun dismissProgress()
    fun goToErrorPage(errorMessage: String)
    fun goToSuccessPage(result: String)
  }

  interface Presenter : MvpPresenter<View> {
    fun addTwoNumbers(firstNumber: String, secondNumber: String)
    fun getView(): View?
  }

  interface Interactor {
    fun addTwoNumbers(firstNumber: String, secondNumber: String) : Observable<Any>
  }
}
