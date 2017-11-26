package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import android.os.Handler
import rx.Observable

class AddActivityMvpInteractor : AddActivityMvpContract.Interactor {

  override fun addTwoNumbers(firstNumber: String, secondNumber: String): Observable<Any> {

    return try {
      val a = Integer.parseInt(firstNumber)
      val b = Integer.parseInt(secondNumber)
      val res = a + b
      Observable.just(res)
    } catch (e: Exception) {
      Observable.error<Any>(Throwable("Something went wrong. Please check the inputs."))
    }
  }
}
