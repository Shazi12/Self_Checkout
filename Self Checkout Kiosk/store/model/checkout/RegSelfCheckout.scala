package store.model.checkout

import store.model.items.Item

class RegSelfCheckout (thecheckout: SelfCheckout) extends CheckoutState(thecheckout){
  /*var code: String = ""
  var map: Map[String, Item] = Map() // create a state variable that maps the barcode to the item
  var shoppingcart: List[Item]= List()
  val error: Item = new Item("error", 0.0)
*/

  override def addItemToStore(barcode: String, item: Item): Unit = {
    // This method adds the item to the store with the corresponding barcode
    this.checkout.map += (barcode ->item)
  }

  override def numberPressed(number: Int): Unit = {
    // TODO
    this.checkout.code += number.toString
  }

  override def clearPressed(): Unit = {
    // TODO
    this.checkout.code = ""
  }

  override def enterPressed(): Unit = {
    // TODO
    //When a customer presses the enter button, the currently displayed barcode should be scanned and the item
    //with that barcode will be added to the customer's cart. The displayed barcode should be be cleared and the
    //empty string "" will be displayed to the customer
    val item: Item = this.checkout.map.getOrElse(this.checkout.code,this.checkout.error)
    this.checkout.shoppingcart = this.checkout.shoppingcart :+item
    this.clearPressed()
    checkout.state = new EnterPressed(checkout)
  }


  override def checkoutPressed(): Unit = {
    checkout.state = new SelfCheckoutPressed(this.checkout)
  }

  override def cashPressed(): Unit = {

  }

  override def creditPressed(): Unit = {

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

  override def displayString(): String = {
    // TODO
    this.checkout.code
  }

  override def itemsInCart(): List[Item] = {
    this.checkout.shoppingcart // adds item to the cart
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

  override def prepareStore(): Unit = {
    // Similar to openMap in the Pale Blue Dot assignment, this method is not required and is
    // meant to help you run manual tests.
    //
    // This method is called by the GUI during setup. Use this method to prepare your
    // items and call addItemToStore to add their barcodes. Also add any sales/tax/etc to your
    // items.
    //
    // This method will not be called during testing and you should not call it in your tests.
    // Each test must setup its own items to ensure compatibility in AutoLab. However, you can
    // write a similar method in your Test Suite classes.

    // Example usage:
    //val testItem: Item = new Item("test item", 100.0)
    //this.addItemToStore("472", testItem)
  }

}
