package ua.com.serious_panda.settings

/**
 * Об’єкт у якому зберігаються методі перетворення у об’єкти настроєк
 */
object PropertiesUtils {

  implicit def tupleToProperties(tuple: (String, String)) = StringProperties(tuple._1, tuple._2)
  implicit def tupleToProperties(tuple: (String, String, String, String)) = StringProperties(tuple._1, tuple._2, tuple._3, tuple._4 )

  implicit def tupleToProperties(tuple: (String, java.util.Locale)) = LocaleProperties(tuple._1, tuple._2)
  implicit def tupleToProperties(tuple: (String, java.util.Locale, String, String)) = LocaleProperties(tuple._1, tuple._2, tuple._3, tuple._4)

  implicit def tupleToProperties(tuple: (String, Boolean)) = BooleanProperties(tuple._1, tuple._2)
  implicit def tupleToProperties(tuple: (String, Boolean, String, String)) = BooleanProperties(tuple._1, tuple._2, tuple._3, tuple._4)

  implicit def tupleToProperties(tuple: (String, Long)) = LongProperties(tuple._1, tuple._2)
  implicit def tupleToProperties(tuple: (String, Long, String, String)) = LongProperties(tuple._1, tuple._2, tuple._3, tuple._4)
}
