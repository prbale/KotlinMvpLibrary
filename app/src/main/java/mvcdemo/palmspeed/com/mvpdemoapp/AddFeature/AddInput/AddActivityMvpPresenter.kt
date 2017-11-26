package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import android.text.TextUtils
import mvclib.palmspeed.com.mvplibrary.MvpBasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AddActivityMvpPresenter : MvpBasePresenter<AddActivityMvpContract.View>(), AddActivityMvpContract.Presenter, AddActivityMvpContract.Interactor.addActivityMvpInteractorCallback {

  private var mAddActivityMvpInteractor: AddActivityMvpContract.Interactor = AddActivityMvpInteractor()

  private var mRouter: AddActivityMvpContract.Router? = null

  override fun addTwoNumbers(firstNumber: String, secondNumber: String) {

    if (isViewAttached) view?.displayProgress()

    if (TextUtils.isEmpty(firstNumber) || TextUtils.isEmpty(secondNumber)) {
      view?.displayError("Please enter valid value.")
      view?.dismissProgress()
      return
    }

    mAddActivityMvpInteractor.addTwoNumbers(firstNumber, secondNumber)
         .subscribeOn(Schedulers.newThread())
         .observeOn(AndroidSchedulers.mainThread())
         .subscribe(
             {
               result -> calculatedAddition(result as Int)
             },
             {
               errorMessage -> displayError(errorMessage.toString())
             },
             {
             }
         )
  }

  override fun setRouter(router: AddActivityMvpContract.Router) {
    mRouter = router
  }

  override fun calculatedAddition(res: Int) {
    if (isViewAttached) {
      view?.dismissProgress()
      view?.displayAddition(res)
      mRouter?.goToSuccessPage(res.toString())
    }
  }

  override fun displayError(errMsg: String) {
    if (isViewAttached) {
      view?.dismissProgress()
      view?.displayError(errMsg)
    }
  }

  override fun start() {
    view?.setRouterToPresenter()
    view?.init()
  }
}
