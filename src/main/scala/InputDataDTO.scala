// TODO: fix Currency to something more appropriate

// TODO: confirm productId can be an Int and not a Long
case class InputDataDTO(productId: Int, productDescr: String,
                        regSingularPrice: Double, promoSingularPrice: Double,
                        regSplitPrice: Double, promoSplitPrice: Double,
                        regForX: Int, promoForX: Int,
                        flags: Seq[Boolean], productSize: String)

object InputDataDTO extends FieldDataParser {

  def fromString(inputData: String) = {
    InputDataDTO(parseNumber(inputData, 0, 8),
      parseString(inputData, 9, 68),
      parseCurrency(inputData, 70, 77),
      parseCurrency(inputData, 79, 86),
      parseCurrency(inputData, 87, 95),
      parseCurrency(inputData, 96, 104),
      parseNumber(inputData, 105, 113),
      parseNumber(inputData, 114, 122),
      parseFlags(inputData, 124, 132),
      parseString(inputData, 133, 142))
  }
}