package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import io.reactivex.Single
import mvclib.palmspeed.com.mvplibrary.MvpPresenter
import mvclib.palmspeed.com.mvplibrary.MvpView

interface AddActivityContract {

  interface View : MvpView {
    fun displayError(errorMessage: String)
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
    fun addTwoNumbers(firstNumber: String, secondNumber: String) : Single<Any>
  }
}
