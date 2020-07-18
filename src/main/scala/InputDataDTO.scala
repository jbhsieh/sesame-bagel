// TODO: fix Currency to something more appropriate

case class InputDataDTO(productId: Int, productDesc: String,
                        regSingularPrice: Double, promoSingularPrice: Double,
                        regSplitPrice: Double, promoSplitPrice: Double,
                        regForX: Int, promoForX: Int,
                        flags: Seq[Boolean], productSize: String)

object InputDataDTO extends FieldDataParser {
  def apply(inputString: String): InputDataDTO =
    new InputDataDTO(parseNumber(inputString, 0, 8),
      parseString(inputString, 9, 68),
      parseCurrency(inputString, 70, 77),
      parseCurrency(inputString, 79, 86),
      parseCurrency(inputString, 87, 95),
      parseCurrency(inputString, 96, 104),
      parseNumber(inputString, 105, 113),
      parseNumber(inputString, 114, 122),
      parseFlags(inputString, 124, 132),
      parseString(inputString, 133, 142))
}
