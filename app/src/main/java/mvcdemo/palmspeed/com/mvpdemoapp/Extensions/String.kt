package mvcdemo.palmspeed.com.mvpdemoapp.Extensions

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by Prashant on 12-11-2017.
 */

fun String.isEmailValid(): Boolean {
  val emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
  val pattern: Pattern = Pattern.compile(emailPattern)
  val matcher: Matcher = pattern.matcher(this)
  return matcher.matches()
}