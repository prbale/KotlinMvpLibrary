package mvcdemo.palmspeed.com.mvpdemoapp.AddFeature.AddResult

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_result.textView1
import mvcdemo.palmspeed.com.mvpdemoapp.R

class AddResultActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_result)
    val result = intent.getStringExtra("RESULT")
    textView1?.text = result
  }
}
