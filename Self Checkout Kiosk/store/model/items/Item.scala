package store.model.items


class Item (var theDescription:String, var basePrice:Double){
  var modifierList: List[Modifier] = List()
  // TODO: Complete this class according to the features listed in the HW document
//A method named "description" that doesn’t take any parameters and returns the description of the item,
// from the constructor, as a String
  def description(): String = {
  theDescription
  }

  //A method named "setBasePrice" that takes a Double and has a return type of Unit.
  // This method will change the base price of this item to the value of the parameter of this method
  def setBasePrice(Bprice:Double): Unit ={
    basePrice = Bprice
  }

//The "price" method should now apply all modifiers to the base price of the item by calling their "updatePrice" methods
  def price(): Double = {
    var newPrice: Double = basePrice
    for(method <-modifierList){
      newPrice = method.updatePrice(newPrice)
    }
    newPrice
  }
//Add a method named "addModifier" that takes a Modifier as a parameter and returns Unit
// After a modifier is added with this method, that modifier should be applied to all future method calls
  def addModifier(modifier: Modifier): Unit={
    modifierList = modifierList :+ modifier
  }

  def tax(): Double = {
    var Tax: Double = 0.0
    for (method <- modifierList) {
      Tax += method.computeTax(price())
    }
    Tax
  }
}

/*abstract class Modifier() {
  def updatePrice (intialPrice: Double): Double
  def computeTax (cost: Double): Double
}
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
}
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
}
class BottleDeposit(deposit:Double) extends Modifier(){
  override def updatePrice(intialPrice: Double): Double = {
    intialPrice
  }

  //The computeTax method should return the deposit amount from the constructor
  override def computeTax(cost: Double): Double = {
    this.deposit//??? The amount of the deposit does not depend on the price of the item
  }
}*/