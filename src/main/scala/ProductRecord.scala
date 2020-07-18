case class ProductRecord(productId: Int, productDesc: String,
                         regularDisplayPrice: String,
                         regularCalcPrice: Double,
                         promoDisplayPrice: Option[String],
                         promoCalcPrice: Option[Double],
                         unitOfMeasure: String,
                         productSize: String,
                         taxRate: Double)

object ProductRecord {
  // flags indices are off by one because of zero vs one indexing
  val PerWeightFlag = 2
  val TaxableFlag = 4

  def decimalRounding(decimals: Double): Double = {
    import scala.math.BigDecimal
    BigDecimal(decimals).setScale(4, BigDecimal.RoundingMode.HALF_DOWN).toDouble
  }
  def convertPricing(singlePrice: Double, splitPrice: Double, forX: Int): Option[(String, Double)] = {
    (singlePrice, splitPrice) match {
      case (0.0, 0.0) => None
      case (single, 0.0) => Some("$%.2f".format(single), single)
      case (0.0, split) => Some("%d for $%.2f".format(forX, split), decimalRounding(splitPrice / forX.toDouble))
      // TODO: case _ => throw some exception
    }
  }

  def apply(inputDTO: InputDataDTO): ProductRecord = {
    val regPricing = convertPricing(inputDTO.regSingularPrice, inputDTO.regSplitPrice, inputDTO.regForX)
    val promoPricing = convertPricing(inputDTO.promoSingularPrice, inputDTO.promoSplitPrice, inputDTO.promoForX)
    val unitOfMeasure = if (inputDTO.flags(PerWeightFlag)) "Pound" else "Each"
    val taxRate = if (inputDTO.flags(TaxableFlag)) 7.775 else 0.0

    new ProductRecord(inputDTO.productId, inputDTO.productDesc, regPricing.get._1, regPricing.get._2,
      promoPricing.map(_._1), promoPricing.map(_._2), unitOfMeasure, inputDTO.productSize, taxRate)
  }
}
