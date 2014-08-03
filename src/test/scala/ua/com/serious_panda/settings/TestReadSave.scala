package ua.com.serious_panda.settings

/**
 * Created by aleo on 03.08.14.
 */
object TestReadSave extends App {
  import java.io.File

  implicit val file = new File("test.properties")
  file.createNewFile()
  file.getAbsolutePath

  val test = StringProperties("testString", "testvalue")

  var v = test.value

  test.value = "none"

  println(test.value)
}
