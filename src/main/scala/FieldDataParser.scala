
trait FieldDataParser {

  // take the next 8 chars and create an Int from them
  def parseNumber(string: String, start: Int, end: Int): Int = {
    val length = end - start
//    assert(length == 8)
    string.substring(start, end).toInt
  }

  def parseString(string: String, start: Int, end: Int): String = {
    val length = end - start
    string.substring(start, end)
  }

  def parseCurrency(string: String, start: Int, end: Int): Double = {
    val length = end - start
    string.substring(start, end).toInt / 100.0
  }

  def parseFlags(string: String, start:Int, end: Int): Seq[Boolean] = {
    val length = end - start
    string.substring(start, end).map[Boolean](_ == 'Y')
  }
}
