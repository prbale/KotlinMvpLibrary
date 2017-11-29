package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import android.text.TextUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput.AddActivityMvpContract.View
import mvclib.palmspeed.com.mvplibrary.MvpBasePresenter

class AddActivityMvpPresenter : MvpBasePresenter<AddActivityMvpContract.View>(), AddActivityMvpContract.Presenter {

  private var mAddActivityMvpInteractor: AddActivityMvpContract.Interactor = AddActivityMvpInteractor()

  override fun addTwoNumbers(firstNumber: String, secondNumber: String) {

    if (TextUtils.isEmpty(firstNumber) || TextUtils.isEmpty(secondNumber)) {
      getView()?.apply {
        displayError("Please enter valid value.")
        dismissProgress()
      }
      return
    }

    addDisposable(mAddActivityMvpInteractor.addTwoNumbers(firstNumber, secondNumber)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
          getView()?.displayProgress()
        }
        .subscribe(
           { result -> calculatedAddition(result as Int) },
           { errorMessage -> handleError(errorMessage.toString()) }
        ))
  }

  fun calculatedAddition(res: Int) {
    getView()?.apply {
      dismissProgress()
      goToSuccessPage(res.toString())
    }
  }

  fun handleError(errMsg: String) {
      getView()?.apply {
        dismissProgress()
        goToErrorPage(errMsg)
      }
  }

  override fun start() {
    getView()?.init()
  }

  override fun getView(): View? = if (isViewAttached) view else null

}
