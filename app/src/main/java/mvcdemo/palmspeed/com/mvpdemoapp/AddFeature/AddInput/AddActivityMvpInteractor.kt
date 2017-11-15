package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import android.os.Handler
import android.text.TextUtils

class AddActivityMvpInteractor(
    private val mListener: AddActivityMvpContract.Interactor.addActivityMvpInteractorCallback) : AddActivityMvpContract.Interactor {

  override fun addTwoNumbers(firstNumber: String, secondNumber: String) {

    if (TextUtils.isEmpty(firstNumber) || TextUtils.isEmpty(secondNumber)) {
      mListener.displayError("Please enter valid value.")
      return
    }

    // Mock login. I'm creating a handler to delay the answer a couple of seconds
    Handler().postDelayed({
      try {
        val a = Integer.parseInt(firstNumber)
        val b = Integer.parseInt(secondNumber)
        val res = a + b
        mListener.calculatedAddition(res)
      } catch (e: Exception) {
        mListener.displayError("Something went wrong.")
      }
    }, 3000)

  }
}
