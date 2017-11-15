package mvcdemo.palmspeed.com.mvpdemoapp.Error

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import mvcdemo.palmspeed.com.mvpdemoapp.R

class ErrorActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_error)

    val errorMessage = intent.getStringExtra("ERROR_MSG")
    val textView = findViewById(R.id.textView1) as TextView
    textView.text = errorMessage
  }
}
