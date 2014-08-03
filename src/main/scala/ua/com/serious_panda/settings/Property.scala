package ua.com.serious_panda.settings

import java.util.Locale

/**
 *
 * Created by aleo on 03.08.14.
 */
case class Property[T](key: String, defaultValue: T) extends PropertiesTrait[T] {

  override var nameResourceBundle: String = null
  override var keyInResourceBundle: String = null

  def this(key: String, defaultValue: T, nameResourceBundle: String, keyInResourceBundle: String) {
    this(key, defaultValue)
    this.nameResourceBundle = nameResourceBundle
    this.keyInResourceBundle = keyInResourceBundle
  }

  /**
   * Метод який перетворює об’єкт у строку
   * @param value об’єкт який необхідно перетворити у строку
   * @return об’єкт перетворений у строку
   */
  override protected def objectToString(value: T): String = {
    value match {
      case x: Long => String.valueOf(x)
      case x: Int => String.valueOf(x)
      case x: Short => String.valueOf(x)
      case x: Byte => String.valueOf(x)
      case x: Double => String.valueOf(x)
      case x: Float => String.valueOf(x)
      case x: String => x
      case x: Boolean => String.valueOf(x)
      case x: java.util.Locale => s"${x.getLanguage}-${x.getCountry}-${x.getVariant}"
      case _ => throw new IllegalArgumentException("Not support Type")
    }
  }

  /**
   * Метод який перетворює строку у об’єкт
   * @param valueString строка яку необхідно перетворити у об’єкт
   * @return об’єкт
   */
  override protected def stringToObject(valueString: String): T = {
    val result = defaultValue match {
      case x: Long => valueString.toLong
      case x: Int => valueString.toInt
      case x: Short => valueString.toShort
      case x: Byte => valueString.toByte
      case x: Double => valueString.toDouble
      case x: Float => valueString.toFloat
      case x: String => valueString
      case x: Boolean => valueString.toBoolean
      case x: java.util.Locale =>
        val arr = valueString.split("-")
        val res: Locale = arr.length match {
          case 1 => new Locale(arr(0))
          case 2 => new Locale(arr(0), arr(1))
          case 3 => new Locale(arr(0), arr(1), arr(2))
          case _ => throw new IllegalArgumentException
        }
        res
      case _ => throw new IllegalArgumentException
    }
    result.asInstanceOf[T]
  }

  def ++(implicit file: java.io.File): T = {
    val oldValue = this.value
    val incValue: AnyVal = oldValue match {
      case x: Long => x + 1
      case x: Int => x + 1
      case x: Short => x + 1
      case x: Byte => x + 1
      case x: Double => x + 1.0
      case x: Float => x + 1.0
      case _ => throw new UnsupportedOperationException
    }
    this.value = incValue.asInstanceOf[T]
    oldValue
  }

  def unary_++(implicit file: java.io.File): T = {
    val incValue: AnyVal = this.value match {
      case x: Long => x + 1
      case x: Int => x + 1
      case x: Short => x + 1
      case x: Byte => x + 1
      case x: Double => x + 1.0
      case x: Float => x + 1.0
      case _ => throw new UnsupportedOperationException
    }
    val newValue = incValue.asInstanceOf[T]
    this.value = newValue
    newValue
  }

  def --(implicit file: java.io.File): T = {
    val oldValue = this.value
    val decValue: AnyVal = oldValue match {
      case x: Long => x - 1
      case x: Int => x - 1
      case x: Short => x - 1
      case x: Byte => x - 1
      case x: Double => x - 1.0
      case x: Float => x - 1.0
      case _ => throw new UnsupportedOperationException
    }
    this.value = decValue.asInstanceOf[T]
    oldValue
  }

  def unary_--(implicit file: java.io.File): T = {
    val decValue: AnyVal = this.value match {
      case x: Long => x - 1
      case x: Int => x - 1
      case x: Short => x - 1
      case x: Byte => x - 1
      case x: Double => x - 1.0
      case x: Float => x - 1.0
      case _ => throw new UnsupportedOperationException
    }
    val newValue = decValue.asInstanceOf[T]
    this.value = newValue
    newValue
  }
}
