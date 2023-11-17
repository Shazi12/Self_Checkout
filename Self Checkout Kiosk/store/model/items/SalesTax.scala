package store.model.items

class SalesTax(percent:Double) extends Modifier(){
  //The updatePrice method to return the input price unmodified
  override def updatePrice(intialPrice: Double): Double = {
    intialPrice
  }
  //The computeTax method should return the amount of sales tax that should be charged based on the inputted price of the item
  override def computeTax(cost: Double): Double = {
    val totalPrice: Double= (this.percent / 100) * cost
    totalPrice
  }
  override def loyaltyOff: Double = {
    0.0
  }
  override def loyaltyOn: Double = {
    0.0
  }
}