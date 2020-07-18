import org.scalatest.{FlatSpec, Matchers}

class FieldDataParserSpec extends FlatSpec with Matchers with FieldDataParser {
  "parseNumber" should "parse a number" in {
    parseNumber("00000100", 1, 8) should equal(100)
    parseNumber("0000010011", 1, 8) should equal(100)
    parseNumber("00000000", 1, 8) should equal(0)
    parseNumber("123456789", 0, 8) should equal(12345678)
    parseNumber("99999999", 1, 8) should equal(9999999)
    }

  "parseString" should "work" in {
    parseString("hip hip hooray hip", 8, 14) should equal("hooray")
  }

  "parseCurrency" should "do the right thing" in {
    parseCurrency("00000167", 1, 8) should equal(1.67)

  }

  "parseFlags" should "return a Boolean array" in {
    val flags = parseFlags("YNNNYN", 1, 6)
    flags should have length(5)
    flags should contain (true)
    flags(3) shouldBe (true) // zero-indexed
  }
}
