package store.model.items

abstract class Modifier() {
  def updatePrice (intialPrice: Double): Double
  def computeTax (cost: Double): Double
  def loyaltyOff: Double
  def loyaltyOn: Double
}

