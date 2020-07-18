import org.scalatest.{FlatSpec, Matchers}

class InputDataDTOSpec extends FlatSpec with Matchers {
  "InputDataDTO" should "create an object" in {
    val exampleString = "19463031 Half Lemonade Half Tea                                      00000850 00000549 00001500 00000000 00000002 00000000 NNNNYNNNN   12x12oz"
    val obj = InputDataDTO.fromString(exampleString)

    obj.productId should be (19463031)
    obj.productDescr should include ("Lemonade")

    obj.regSingularPrice shouldBe 8.5
    obj.regSplitPrice shouldBe 15.0
    obj.regForX shouldBe 2

    obj.promoSingularPrice shouldBe 5.49
    obj.promoSplitPrice shouldBe 0
    obj.promoForX shouldBe 0

    obj.flags should contain(false)
    obj.flags should contain(true)
    obj.flags(3) should equal (true)
    obj.productSize should equal ("12x12oz")
  }
}
