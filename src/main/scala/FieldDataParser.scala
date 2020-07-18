
trait FieldDataParser {

  // keep the indices all zero-based because it's Scala and since given inclusive change is required either way.
  // (field/type, string, indices)
  // pass in parserType or how to infer?
  def parse[B](bParser: (String)=>B, string: String, start: Int, end: Int): B = {
    bParser(string.substring(start, end))
  }

  // take the next 8 chars and create an Int from them
  def parseNumber(string: String, start: Int, end: Int): Int = {
    parse(_.toInt, string, start, end)
  }

  def parseString(string: String, start: Int, end: Int): String = {
    parse((s: String) => s, string, start, end)
  }

  def parseCurrency(string: String, start: Int, end: Int): Double = {
    parse((_.toInt / 100.0), string, start, end)
  }

  def parseFlags(string: String, start:Int, end: Int): Seq[Boolean] = {
    val flagParser = (string: String) => string.map[Boolean](_ == 'Y')
    parse(flagParser, string, start, end)
  }
}
