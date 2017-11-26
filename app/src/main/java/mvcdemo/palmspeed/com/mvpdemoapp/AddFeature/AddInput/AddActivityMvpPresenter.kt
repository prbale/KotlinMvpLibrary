package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import android.text.TextUtils
import mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput.AddActivityMvpContract.View
import mvclib.palmspeed.com.mvplibrary.MvpBasePresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class AddActivityMvpPresenter : MvpBasePresenter<AddActivityMvpContract.View>(), AddActivityMvpContract.Presenter, AddActivityMvpContract.Interactor.addActivityMvpInteractorCallback {

  private var mAddActivityMvpInteractor: AddActivityMvpContract.Interactor = AddActivityMvpInteractor()

  private var mRouter: AddActivityMvpContract.Router? = null

  override fun addTwoNumbers(firstNumber: String, secondNumber: String) {

    getView()?.displayProgress()

    if (TextUtils.isEmpty(firstNumber) || TextUtils.isEmpty(secondNumber)) {
      getView()?.displayError("Please enter valid value.")
      getView()?.dismissProgress()
      return
    }

    mAddActivityMvpInteractor.addTwoNumbers(firstNumber, secondNumber)
       .subscribeOn(Schedulers.newThread())
       .observeOn(AndroidSchedulers.mainThread())
       .subscribe(
           { result -> calculatedAddition(result as Int) },
           { errorMessage -> displayError(errorMessage.toString()) },
           {}
       )
  }

  override fun setRouter(router: AddActivityMvpContract.Router) {
    mRouter = router
  }

  override fun calculatedAddition(res: Int) {
      getView()?.dismissProgress()
      getView()?.displayAddition(res)
      mRouter?.goToSuccessPage(res.toString())
  }

  override fun displayError(errMsg: String) {
      getView()?.dismissProgress()
      getView()?.displayError(errMsg)
  }

  override fun start() {
    getView()?.setRouterToPresenter()
    getView()?.init()
  }

  override fun getView(): View? = if (isViewAttached) view else null

}
