package store.model.items

//A constructor that takes a Double representing the percentage of the sale
class Sale(percent:Double) extends Modifier() {
  //The updatePrice method should take the price of an item before the sale is applied and return the new price with the sale applied
  override def updatePrice(intialPrice: Double): Double = {
    val newPrice: Double = ((100 - this.percent) / 100) * intialPrice
    newPrice
  }
  override def computeTax(cost: Double): Double = {
    val zero:Double= 0.0
    zero
  }
  override def loyaltyOff: Double = {
    0.0
  }
  override def loyaltyOn: Double = {
    0.0
  }
}
