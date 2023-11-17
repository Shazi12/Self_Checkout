package store.model.items

class LoyaltySale(var loyalperc: Double) extends Modifier {
  //
  var flag: Double = 0.0

  override def updatePrice (beforemod: Double): Double = {
    val flagperc: Double = loyalperc * flag
    val percent: Double = 100 - flagperc     // gets the percentage the customer must pay after the sale is applied.
    val truepercent: Double = percent * 0.01    // changes percent into decimal
    val finalsale: Double = beforemod * truepercent // calculates the price the customer pays after sale applied.
    finalsale

    // perc x flag if 0 = 0
    // perc x flag if 1 = perc
  }

  override def computeTax(itemprice: Double): Double = {
    // There is no tax applied by a loyalty sale.
    0.0
  }

  def loyaltyOff: Double = {
    flag = 0.0
    flag
  }
  def loyaltyOn: Double = {
    flag = 1.0
    flag
  }
}
