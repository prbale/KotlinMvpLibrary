package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddInput

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.btnAbout
import kotlinx.android.synthetic.main.activity_main.btnAdd
import kotlinx.android.synthetic.main.activity_main.mainActivityRootLayout
import kotlinx.android.synthetic.main.activity_main.txtNumber1
import kotlinx.android.synthetic.main.activity_main.txtNumber2
import mvcdemo.palmspeed.com.mvpdemoapp.About.View.AboutActivity
import mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddResult.AddResultActivity
import mvcdemo.palmspeed.com.mvpdemoapp.Base.BaseActivity
import mvcdemo.palmspeed.com.mvpdemoapp.Error.ErrorActivity
import mvcdemo.palmspeed.com.mvpdemoapp.Extensions.hideKeyboard
import mvcdemo.palmspeed.com.mvpdemoapp.Extensions.setFullScreen
import mvcdemo.palmspeed.com.mvpdemoapp.Extensions.showSnackBarLong
import mvcdemo.palmspeed.com.mvpdemoapp.R
import mvcdemo.palmspeed.com.mvpdemoapp.R.string

class AddActivity : BaseActivity<AddActivityContract.View, AddActivityContract.Presenter>(), AddActivityContract.View {

  override fun createPresenter(): AddActivityContract.Presenter = AddActivityPresenter()

  override fun getViewRef(): AddActivityContract.View = this

  override fun tag(): String = AddActivity::class.java.simpleName

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setFullScreen()
    setContentView(R.layout.activity_main)

    with(presenter) {
      start()
      attachView(this@AddActivity)
    }

    btnAdd?.setOnClickListener {
      hideKeyboard()
      presenter.addTwoNumbers(
          firstNumber = txtNumber1?.text.toString(),
          secondNumber = txtNumber2?.text.toString()
      )
    }

    btnAbout?.setOnClickListener {
      startActivity(Intent(this@AddActivity, AboutActivity::class.java))
    }
  }

  override fun init() {
    // Do some initialization here.
  }

  // Clear CompositeDisposal if any.
  override fun onStop() {
    super.onStop()
    presenter.stop()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView(false)
  }

  override fun displayError(errorMessage: String) =
      mainActivityRootLayout.showSnackBarLong(errorMessage)

  override fun displayInvalidInputError() =
      mainActivityRootLayout.showSnackBarLong(getString(string.error_msg_invalid_values))

  override fun displayProgress() = showLoading()

  override fun dismissProgress() = hideLoading()

  override fun goToErrorPage(errorMessage: String) =
      startActivity(ErrorActivity.newIntent(this, errorMessage))

  override fun goToSuccessPage(result: String) =
    startActivity(AddResultActivity.newIntent(this, result))

}
