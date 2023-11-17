package store.model.items

class BottleDeposit(deposit:Double) extends Modifier(){
  override def updatePrice(intialPrice: Double): Double = {
    intialPrice
  }

  //The computeTax method should return the deposit amount from the constructor
  override def computeTax(cost: Double): Double = {
    this.deposit//??? The amount of the deposit does not depend on the price of the item
  }
  override def loyaltyOff: Double = {
    0.0
  }
  override def loyaltyOn: Double = {
    0.0
  }
}
