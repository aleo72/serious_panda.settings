package ua.com.serious_panda.settings

/*
 * Created by aleo on 03.08.14.
 */


case class StringProperties(key: String, defaultValue: String, nameResourceBundle: String = null, keyInResourceBundle: String = null) extends Properties[String] {

  override protected def stringToObject(valueString: String): String = valueString

  override protected def objectToString(value: String): String = value

}

case class BooleanProperties(key: String, defaultValue: Boolean, nameResourceBundle: String = null, keyInResourceBundle: String = null) extends Properties[Boolean] {

  override protected def stringToObject(valueString: String): Boolean = valueString.toBoolean

  override protected def objectToString(value: Boolean): String = value.toString

}

case class LocaleProperties(key: String, defaultValue: java.util.Locale, nameResourceBundle: String = null, keyInResourceBundle: String = null) extends Properties[java.util.Locale] {

  override protected def stringToObject(valueString: String): java.util.Locale = {
    val a = valueString.split("-")
    new java.util.Locale(a(0), a(1))
  }

  override protected def objectToString(value: java.util.Locale): String = s"${value.getLanguage}-${value.getCountry}"
}

case class LongProperties(key: String, defaultValue: Long, nameResourceBundle: String = null, keyInResourceBundle: String = null) extends Properties[Long] {

  override protected def stringToObject(valueString: String): Long = valueString.toLong

  override protected def objectToString(value: Long): String = value.toString

  def inc(implicit file: java.io.File) = {
    val v = this.value
    this.value = v + 1
    v
  }
}
