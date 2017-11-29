package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import io.reactivex.Single

class AddActivityInteractor : AddActivityContract.Interactor {

  override fun addTwoNumbers(firstNumber: String, secondNumber: String): Single<Any> = try {
      val a = Integer.parseInt(firstNumber)
      val b = Integer.parseInt(secondNumber)
      val res = a + b
      Single.just(res)
    } catch (e: Exception) {
      Single.error<Any>(Throwable("Something went wrong. Please check the inputs."))
    }
}

