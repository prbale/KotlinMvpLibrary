package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddResult

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.textView1
import mvcdemo.palmspeed.com.mvpdemoapp.R

class AddResultActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)
    val result = intent.getStringExtra(ADDITION_RESULT) ?:
        throw IllegalStateException("field $ADDITION_RESULT missing in Intent")
    textView1?.text = result
  }

  companion object {
    private val ADDITION_RESULT = "ADDITION_RESULT"

    fun newIntent(context: Context, additionResult: String) : Intent {
      val intent = Intent(context, AddResultActivity::class.java)
      intent.putExtra(ADDITION_RESULT, additionResult)
      return intent
    }
  }
}
