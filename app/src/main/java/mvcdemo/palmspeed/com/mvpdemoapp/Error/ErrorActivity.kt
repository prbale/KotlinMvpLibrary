package mvcdemo.palmspeed.com.mvpdemoapp.Error

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddResult.AddResultActivity
import mvcdemo.palmspeed.com.mvpdemoapp.R

class ErrorActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_error)

    val errorMessage = intent.getStringExtra("ERROR_MESSAGE")
    val textView = findViewById(R.id.textView1) as TextView
    textView.text = errorMessage
  }

  companion object {
    private val ERROR_MESSAGE = "ERROR_MESSAGE"

    fun newIntent(context: Context, errorMessage: String) : Intent {
      val intent = Intent(context, ErrorActivity::class.java)
      intent.putExtra(ERROR_MESSAGE, errorMessage)
      return intent
    }
  }
}
