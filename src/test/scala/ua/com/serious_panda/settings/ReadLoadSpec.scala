package ua.com.serious_panda.settings

import java.io.File
import java.util.Locale

import org.scalatest._

/**
 * Тест для перевірки запису та считування настроєк
 * Created by aleo on 03.08.14.
 */
class ReadLoadSpec extends FlatSpec with Matchers {

  val dir = "./src/test/resources"
  val fileDir = new File(dir)
  implicit val file = new File(s"$dir/test.properties")

  fileDir.mkdirs()

  if (file.exists()) file.delete()

  "Property[String]" should "save and load string properties" in {
    val key = "key.string"
    val stringFirstValue = "stringFirstValue"
    val stringSecondValue = "stringSecondValue"

    val prop = Property[String](key, stringFirstValue)

    prop.value should be(stringFirstValue)

    prop.value = stringSecondValue

    prop.value should be(stringSecondValue)

    a[UnsupportedOperationException] shouldBe thrownBy {
      prop.++
    }
  }

  "Property[Long]" should "save and load Long properties" in {
    val key = "key.long"
    val firstValue = 1L
    val secondValue = 2L

    val prop = Property[Long](key, firstValue)
    prop.value should be(firstValue)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.++ should be(secondValue)
    prop.value should be(secondValue + 1)
    prop.unary_++ should be(secondValue + 1 + 1)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.-- should be(secondValue)
    prop.value should be(secondValue - 1)
    prop.unary_-- should be(secondValue - 1 - 1)
  }

  "Property[Int]" should "save and load Int properties" in {
    val key = "key.Int"
    val firstValue = 1
    val secondValue = 2

    val prop = Property[Int](key, firstValue)
    prop.value should be(firstValue)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.++ should be(secondValue)
    prop.value should be(secondValue + 1)
    prop.unary_++ should be(secondValue + 1 + 1)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.-- should be(secondValue)
    prop.value should be(secondValue - 1)
    prop.unary_-- should be(secondValue - 1 - 1)
  }

  "Property[Short]" should "save and load Short properties" in {
    val key = "key.Short"
    val firstValue: Short = 1
    val secondValue: Short = 2

    val prop = Property[Short](key, firstValue)
    prop.value should be(firstValue)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.++ should be(secondValue)
    prop.value should be(secondValue + 1)
    prop.unary_++ should be(secondValue + 1 + 1)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.-- should be(secondValue)
    prop.value should be(secondValue - 1)
    prop.unary_-- should be(secondValue - 1 - 1)
  }

  "Property[Byte]" should "save and load Byte properties" in {
    val key = "key.Byte"
    val firstValue: Byte = 1
    val secondValue: Byte = 2

    val prop = Property[Byte](key, firstValue)
    prop.value should be(firstValue)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.++ should be(secondValue)
    prop.value should be(secondValue + 1)
    prop.unary_++ should be(secondValue + 1 + 1)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.-- should be(secondValue)
    prop.value should be(secondValue - 1)
    prop.unary_-- should be(secondValue - 1 - 1)
  }

  "Property[Double]" should "save and load Double properties" in {
    val key = "key.Double"
    val firstValue = 1.0
    val secondValue = 2.0

    val prop = Property[Double](key, firstValue)
    prop.value should be(firstValue)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.++ should be(secondValue)
    prop.value should be(secondValue + 1.0)
    prop.unary_++ should be(secondValue + 1.0 + 1.0)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.-- should be(secondValue)
    prop.value should be(secondValue - 1.0)
    prop.unary_-- should be(secondValue - 1.0 - 1.0)
  }

  "Property[Float]" should "save and load Float properties" in {
    val key = "key.Float"
    val firstValue = 1.0F
    val secondValue = 2.0F

    val prop = Property[Float](key, firstValue)
    prop.value should be(firstValue)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.++ should be(secondValue)
    prop.value should be(secondValue + 1.0)
    prop.unary_++ should be(secondValue + 1.0 + 1.0)

    prop.value = secondValue

    prop.value should be(secondValue)
    prop.-- should be(secondValue)
    prop.value should be(secondValue - 1.0)
    prop.unary_-- should be(secondValue - 1.0 - 1.0)
  }

  "Property[Boolean]" should "save and load Boolean properties" in {
    val key = "key.Boolean"
    val firstValue = true
    val secondValue = false

    val prop = Property[Boolean](key, firstValue)
    prop.value should be(firstValue)

    prop.value = secondValue

    prop.value should be(secondValue)
  }

  "Property[Locale]" should "save and load Locale properties" in {
    val key = "key.Locale"
    val firstValue = Locale.CANADA_FRENCH
    val secondValue = Locale.KOREAN
    val uk = new Locale("uk", "UK")

    val prop = Property[Locale](key, firstValue)

    prop.value shouldBe firstValue

    prop.value = secondValue

    prop.value shouldBe secondValue

    prop.value = uk
    prop.value shouldBe uk

    a[UnsupportedOperationException] shouldBe thrownBy {
      prop.++
    }
  }

}
