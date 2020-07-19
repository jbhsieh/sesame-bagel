import org.scalatest.{FlatSpec, Matchers}

class ProductRecordSpec extends FlatSpec with Matchers {
  val falseFlags = List.fill(8)(false)
  val flagsWithWeight = falseFlags.updated(ProductRecord.PerWeightFlag, true)
  val flagsWithTax = falseFlags.updated(ProductRecord.TaxableFlag, true)

  "ProductRecord from data" should "always have regular pricing info" in {
    val inputEggplant = InputDataDTO(98765, "eggplant", 1.99, 0, 0, 0, 0, 0, flagsWithWeight, "a globe")
    val outEggplant = ProductRecord(inputEggplant)
    outEggplant.regularDisplayPrice shouldBe "$1.99"
    outEggplant.regularCalcPrice shouldBe 1.99
    outEggplant.unitOfMeasure shouldBe "Pound"
    outEggplant.promoCalcPrice shouldBe None

    val inputCheese = InputDataDTO(888888, "cheesy cheese", 0, 0, 1.00, 0, 8, 0, falseFlags, "stick")
    val outputCheese = ProductRecord(inputCheese)
    outputCheese.regularDisplayPrice shouldBe "8 for $1.00"
    outputCheese.regularCalcPrice shouldBe 0.125
    outputCheese.unitOfMeasure shouldBe "Each"
  }
  it should "also have promo pricing when available" in {
    val inputSugar = InputDataDTO(76567, "sweet sugar", 4.95, 3.50, 0, 0, 0, 0, falseFlags, "5 pounds")
    val outputSugar = ProductRecord(inputSugar)
    outputSugar.regularDisplayPrice shouldBe "$4.95"
    outputSugar.regularCalcPrice shouldBe 4.95
    outputSugar.promoDisplayPrice shouldBe Some("$3.50")
    outputSugar.promoCalcPrice shouldBe Some(3.50)
  }
  it should "have tax on shampoo" in {
    val shampoo = ProductRecord(InputDataDTO(34366, "shampoo", 8.99, 0, 0, 0, 0, 0, flagsWithTax, "7oz"))
    shampoo.taxRate shouldBe 7.775
  }

  "convertPricing" should "support single pricing" in {
    val (displayString, calcPrice) = ProductRecord.convertPricing(24.99, 0, 0).get
    displayString shouldBe "$24.99"
    calcPrice shouldBe 24.99
  }
  it should "support for split pricing" in {
    val (displayString: String, calcPrice: Double) = ProductRecord.convertPricing(0, 10.00, 3).get
    displayString shouldBe "3 for $10.00"
    calcPrice shouldBe 3.3333
  }
  it should "return None if has neither pricing value" in {
    ProductRecord.convertPricing(0, 0, 0) shouldBe None
  }
  it should "throw an exception if it has both pricing types" in {
    the [RuntimeException] thrownBy (ProductRecord.convertPricing(10.00, 20.00, 2)) should have message ("cannot have both single and split pricing")
  }
}
