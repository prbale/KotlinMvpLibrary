package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import mvclib.palmspeed.com.mvplibrary.MvpBasePresenter

class AddActivityMvpPresenter : MvpBasePresenter<AddActivityMvpContract.View>(), AddActivityMvpContract.Presenter, AddActivityMvpContract.Interactor.addActivityMvpInteractorCallback {

  private var mAddActivityMvpInteractor: AddActivityMvpContract.Interactor = AddActivityMvpInteractor(this)

  private var mRouter: AddActivityMvpContract.Router? = null

  override fun addTwoNumbers(firstNumber: String, secondNumber: String) {
    if (isViewAttached) view?.displayProgress()
    mAddActivityMvpInteractor.addTwoNumbers(firstNumber, secondNumber)
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
