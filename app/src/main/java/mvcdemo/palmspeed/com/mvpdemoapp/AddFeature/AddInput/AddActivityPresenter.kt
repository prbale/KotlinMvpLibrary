package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import android.text.TextUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput.AddActivityContract.View
import mvclib.palmspeed.com.mvplibrary.MvpBasePresenter

class AddActivityPresenter : MvpBasePresenter<AddActivityContract.View>(), AddActivityContract.Presenter {

  private var mAddActivityInteractor: AddActivityContract.Interactor = AddActivityInteractor()

  override fun addTwoNumbers(firstNumber: String, secondNumber: String) {

    if (TextUtils.isEmpty(firstNumber) || TextUtils.isEmpty(secondNumber)) {
      getView()?.apply {
        displayError("Please enter valid value.")
        dismissProgress()
      }
      return
    }

    addDisposable(mAddActivityInteractor.addTwoNumbers(firstNumber, secondNumber)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSubscribe {
          getView()?.displayProgress()
        }
        .doFinally {
          getView()?.dismissProgress()
        }
        .subscribe(
           { result -> calculatedAddition(result as Int) },
           { errorMessage -> handleError(errorMessage.toString()) }
        ))
  }

  fun calculatedAddition(res: Int) {
    getView()?.apply {
      goToSuccessPage(res.toString())
    }
  }

  fun handleError(errMsg: String) {
      getView()?.apply {
        goToErrorPage(errMsg)
      }
  }

  override fun start() {
    getView()?.init()
  }

  override fun getView(): View? = if (isViewAttached) view else null

}
