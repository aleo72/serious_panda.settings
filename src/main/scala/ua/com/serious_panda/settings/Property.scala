package ua.com.serious_panda.settings

import java.awt.Dimension
import java.awt.event.{ActionEvent, ActionListener}
import java.util.Locale
import javax.swing._
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
 *
 * Created by aleo on 03.08.14.
 */
case class Property[T](key: String, defaultValue: T) extends PropertiesTrait[T] {

  def this(key: String, defaultValue: T, helpText: Option[String] = None, nameResourceBundle: String = null, keyInResourceBundle: String = null) {
    this(key, defaultValue)
    this.nameResourceBundle = nameResourceBundle
    this.keyInResourceBundle = keyInResourceBundle
    this._helpText = helpText
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
      case x: java.util.Locale => s"${x.getLanguage}_${x.getCountry}_${x.getVariant}"
      case _ => throw new IllegalArgumentException("Not support Type")
    }
  }

  /**
   * Метод який перетворює строку у об’єкт
   * @param valueString строка яку необхідно перетворити у об’єкт
   * @return об’єкт
   */
  override protected def stringToObject(valueString: String): T = {
    val res = this match {
      case Property(key: String, defaultValue: Long) => valueString.toLong
      case Property(key: String, defaultValue: Int) => valueString.toInt
      case Property(key: String, defaultValue: Short) => valueString.toShort
      case Property(key: String, defaultValue: Byte) => valueString.toByte
      case Property(key: String, defaultValue: Float) => valueString.toFloat
      case Property(key: String, defaultValue: Double) => valueString.toDouble
      case Property(key: String, defaultValue: String) => valueString
      case Property(key: String, defaultValue: Boolean) => valueString.toBoolean
      case Property(key: String, defaultValue: Locale) =>
        val arr = valueString.split("_")
        arr.length match {
          case 1 => new Locale(arr(0))
          case 2 => new Locale(arr(0), arr(1))
          case 3 => new Locale(arr(0), arr(1), arr(2))
          case _ => throw new IllegalArgumentException
        }
      case _ => throw new IllegalArgumentException
    }
    res.asInstanceOf[T]
  }

  def inc(implicit file: java.io.File): T = {
    val oldValue = this.value
    val v = this match {
      case x@Property(key: String, defaultValue: Long) => oldValue.asInstanceOf[Long] + 1L
      case x@Property(key: String, defaultValue: Int) => oldValue.asInstanceOf[Int] + 1
      case x@Property(key: String, defaultValue: Short) => oldValue.asInstanceOf[Short] + 1
      case x@Property(key: String, defaultValue: Byte) => oldValue.asInstanceOf[Byte] + 1
      case x@Property(key: String, defaultValue: Float) => oldValue.asInstanceOf[Float] + 1
      case x@Property(key: String, defaultValue: Double) => oldValue.asInstanceOf[Double] + 1.0
      case _ => throw new UnsupportedOperationException
    }
    val newValue = v.asInstanceOf[T]
    this.value = newValue
    newValue
  }

  def dec(implicit file: java.io.File): T = {
    val oldValue = this.value
    val v = this match {
      case x@Property(key: String, defaultValue: Long) => oldValue.asInstanceOf[Long] - 1L
      case x@Property(key: String, defaultValue: Int) => oldValue.asInstanceOf[Int] - 1
      case x@Property(key: String, defaultValue: Short) => oldValue.asInstanceOf[Short] - 1
      case x@Property(key: String, defaultValue: Byte) => oldValue.asInstanceOf[Byte] - 1
      case x@Property(key: String, defaultValue: Float) => oldValue.asInstanceOf[Float] - 1
      case x@Property(key: String, defaultValue: Double) => oldValue.asInstanceOf[Double] - 1.0
      case _ => throw new UnsupportedOperationException
    }
    val newValue = v.asInstanceOf[T]
    this.value = newValue
    newValue
  }

  override def editableComponent(implicit file: java.io.File): JComponent = {
    var component: JComponent = null

    this match {
      case Property(key: String, defaultValue: String) =>
        component = new JTextField(this.value.toString)
        component.asInstanceOf[JTextField].addActionListener(new ActionListener {
          override def actionPerformed(event: ActionEvent): Unit = {
            value = stringToObject(component.asInstanceOf[JTextField].getText)
          }
        })
      case Property(key: String, defaultValue: Long) =>
        component = new JSpinner(new SpinnerNumberModel(this.value.asInstanceOf[Long], Long.MinValue, Long.MaxValue, 1))
        component.asInstanceOf[JSpinner].addChangeListener(new ChangeListener {
          override def stateChanged(event: ChangeEvent): Unit = {
            value = stringToObject(event.getSource.asInstanceOf[JSpinner].getValue.toString.toDouble.toLong.toString)
          }
        })
      case Property(key: String, defaultValue: Int) =>
        component = new JSpinner(new SpinnerNumberModel(this.value.asInstanceOf[Int], Int.MinValue, Int.MaxValue, 1))
        component.asInstanceOf[JSpinner].addChangeListener(new ChangeListener {
          override def stateChanged(event: ChangeEvent): Unit = {
            value = stringToObject(event.getSource.asInstanceOf[JSpinner].getValue.toString.toDouble.toInt.toString)
          }
        })
      case Property(key: String, defaultValue: Short) =>
        component = new JSpinner(new SpinnerNumberModel(this.value.asInstanceOf[Short], Short.MinValue, Short.MaxValue, 1))
        component.asInstanceOf[JSpinner].addChangeListener(new ChangeListener {
          override def stateChanged(event: ChangeEvent): Unit = {
            value = stringToObject(event.getSource.asInstanceOf[JSpinner].getValue.toString.toDouble.toShort.toString)
          }
        })
      case Property(key: String, defaultValue: Byte) =>
        component = new JSpinner(new SpinnerNumberModel(this.value.asInstanceOf[Byte], Byte.MinValue, Byte.MaxValue, 1))
        component.asInstanceOf[JSpinner].addChangeListener(new ChangeListener {
          override def stateChanged(event: ChangeEvent): Unit = {
            value = stringToObject(event.getSource.asInstanceOf[JSpinner].getValue.toString.toDouble.toByte.toString)
          }
        })
      case Property(key: String, defaultValue: Float) =>
        component = new JSpinner(new SpinnerNumberModel(this.value.asInstanceOf[Float], Long.MinValue, Long.MaxValue, 0.5))
        component.asInstanceOf[JSpinner].addChangeListener(new ChangeListener {
          override def stateChanged(event: ChangeEvent): Unit = {
            value = stringToObject(event.getSource.asInstanceOf[JSpinner].getValue.toString)
          }
        })
      case Property(key: String, defaultValue: Double) =>
        component = new JSpinner(new SpinnerNumberModel(this.value.asInstanceOf[Double], Long.MinValue, Long.MaxValue, 0.5))
        component.asInstanceOf[JSpinner].addChangeListener(new ChangeListener {
          override def stateChanged(event: ChangeEvent): Unit = {
            value = stringToObject(event.getSource.asInstanceOf[JSpinner].getValue.toString)
          }
        })

      case Property(key: String, defaultValue: Boolean) =>
        component = new JCheckBox()
        component.asInstanceOf[JCheckBox].setSelected(this.value.asInstanceOf[Boolean])
        component.asInstanceOf[JCheckBox].addActionListener(new ActionListener {
          override def actionPerformed(event: ActionEvent): Unit = {
            value = component.asInstanceOf[JCheckBox].isSelected.asInstanceOf[T]
          }
        })
      case Property(key: String, defaultValue: Locale) =>
        component = new JComboBox[Locale](Locale.getAvailableLocales)
        component.asInstanceOf[JComboBox[Locale]].setSelectedItem(this.value.asInstanceOf[Locale])
        component.asInstanceOf[JComboBox[Locale]].addActionListener(new ActionListener {
          override def actionPerformed(event: ActionEvent): Unit = {
            value = event.getSource.asInstanceOf[JComboBox[Locale]].getSelectedItem.asInstanceOf[T]
          }
        })
      case _ => ???
    }
    component.setEnabled(this.editable)
    component
  }
}
