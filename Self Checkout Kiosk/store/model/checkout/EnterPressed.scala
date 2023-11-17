package store.model.checkout
import store.model.items.Item

class EnterPressed(thecheckout:SelfCheckout) extends CheckoutState (thecheckout){
  //val multiEnterPress = this.checkout.newItem
  override def addItemToStore(barcode: String, item: Item): Unit = {
    this.checkout.state = new RegSelfCheckout(this.checkout)
    checkout.addItemToStore(barcode,item)
  }

  override def itemsInCart(): List[Item] = {
    this.checkout.state = new RegSelfCheckout(this.checkout)
    checkout.itemsInCart()
  }

  override def numberPressed(number: Int): Unit = {
    //returns checkout to the regular state
    checkout.state = new RegSelfCheckout(this.checkout)
    checkout.numberPressed(number)

  }
  override def clearPressed(): Unit = {
    this.checkout.shoppingcart = checkout.shoppingcart :+ checkout.error
    checkout.state = new RegSelfCheckout(this.checkout)
  }

  override def displayString(): String = {
    this.checkout.state = new RegSelfCheckout(this.checkout)
    checkout.displayString()
  }
  override def enterPressed(): Unit = {
    // adds the last element of the list again
    this.checkout.shoppingcart = this.checkout.shoppingcart :+ this.checkout.shoppingcart.last
    this.checkout.code = ""
  }
/*
  override def checkoutPressed(): Unit = {
    thecheckout.state = new SelfCheckoutPressed(thecheckout)
    checkoutPressed()
  } */

  override def cashPressed(): Unit = {
    //checkout.state = new RegSelfCheckout(this.checkout)
  }

  override def creditPressed(): Unit = {
    //checkout.state = new RegSelfCheckout(this.checkout)
  }

  override def loyaltyCardPressed(): Unit = {
    //checkout.state = new RegSelfCheckout(this.checkout)
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


  override def checkoutPressed(): Unit = {
    this.checkout.code = "cash or credit"
    this.checkout.state = new SelfCheckoutPressed(this.checkout)
  }

}
