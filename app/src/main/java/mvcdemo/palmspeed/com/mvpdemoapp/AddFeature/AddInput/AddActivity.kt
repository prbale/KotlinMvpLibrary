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

class AddActivity : BaseActivity<AddActivityMvpContract.View, AddActivityMvpContract.Presenter>(), AddActivityMvpContract.View, AddActivityMvpContract.Router {

  override fun createPresenter(): AddActivityMvpContract.Presenter = AddActivityMvpPresenter()

  override fun getViewRef(): AddActivityMvpContract.View = this

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
    initFields()
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView(false)
  }

  private fun initFields() {
    // Some initializations for this activity.
  }

  override fun displayAddition(res: Int) {}

  override fun displayError(errMsg: String) {
    mainActivityRootLayout.showSnackBarLong(errMsg)
  }

  override fun displayProgress() = showLoading()

  override fun dismissProgress() = hideLoading()

  override fun setRouterToPresenter() = presenter.setRouter(this)

  override fun goToErrorPage(errorMessage: String) {
    val intent = Intent(this, ErrorActivity::class.java).apply {
      putExtra("ERROR_MSG", errorMessage)
    }
    startActivity(intent)
  }

  override fun goToSuccessPage(result: String) {
    val intent = Intent(this, AddResultActivity::class.java).apply {
      putExtra("RESULT", result)
    }
    startActivity(intent)
  }
}
