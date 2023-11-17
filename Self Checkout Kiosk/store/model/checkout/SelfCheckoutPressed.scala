package store.model.checkout
import store.model.items.Item

class SelfCheckoutPressed(thecheckout:SelfCheckout) extends CheckoutState (thecheckout){

  override def addItemToStore(barcode: String, item: Item): Unit = {
    this.checkout.map = this.checkout.map + (barcode->item)
  }

  override def itemsInCart(): List[Item] = {
    clearPressed()
    this.checkout.shoppingcart
  }
  override def displayString(): String = {
    // Return the string "cash or credit" when checkout is Pressed
    this.checkout.code = "cash or credit"
    this.checkout.code
  }

  override def numberPressed(number: Int): Unit = {
    //Have no effect
  }

  override def subtotal(): Double = {
    var newPrice: Double =0.0
    for(i <- itemsInCart()){
      newPrice += i.price()
    }
    newPrice
  }


  override def tax(): Double = {
    var tax:Double = 0.0
    for(i <- itemsInCart()){
      tax += i.tax()
    }
    tax
  }

  override def total(): Double = {
    subtotal() + tax()
  }

  override def prepareStore(): Unit ={}

  override def enterPressed(): Unit ={
    //Have no effect
  }
  override def clearPressed(): Unit ={
    //Have no effect
  }

  override def cashPressed(): Unit = {
    // TODO
    this.checkout.shoppingcart = List()  // empty cart for next costumer
    this.checkout.code = "" // turn's display blank again
    this.checkout.state = new RegSelfCheckout(this.checkout) // brings back to the initial state
  }

  override def creditPressed(): Unit = { // has same function as creditPressed() above.
    this.checkout.shoppingcart = List()
    this.checkout.code = ""
    for (i <- checkout.map.values) {
      for (m <- i.modifierList) {
        m.loyaltyOff                   // TURNS LOYALTY OFF FOR ALL VALUES
      }
    }
    this.checkout.state = new RegSelfCheckout(this.checkout)

  }

  override def loyaltyCardPressed(): Unit = {
    // THIS BUTTON SHOULD NOW SWITCH THE FUNCTIONALITY OF LOYALTY SALE
    // We should be switching the flag to 1 now.
    for (i <- checkout.map.values) {
      for (m <- i.modifierList) {
        m.loyaltyOn                  // TURNS LOYALTY ON FOR ALL VALUES
      }
    }
    // This will now allow loyaltySale to function on all items.
    for (i <- this.checkout.shoppingcart) {
      i.price()
      i.tax()
    }
    // This will make sure all the items in the cart are updated.
  }
}
