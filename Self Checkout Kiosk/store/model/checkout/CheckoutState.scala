package store.model.checkout

import store.model.items.Item

//abstract class that has every method in checkout
//*********************************************************
abstract class CheckoutState(val checkout: SelfCheckout) {

  def addItemToStore(barcode: String, item: Item): Unit = {}

  def numberPressed(number: Int): Unit = {}

  def clearPressed(): Unit = {}

  def enterPressed(): Unit = {}

  def checkoutPressed(): Unit = {}

  def cashPressed(): Unit = {}

  def creditPressed(): Unit = {}

  def loyaltyCardPressed(): Unit = {}

  def displayString(): String = {this.checkout.displayString()}

  def itemsInCart(): List[Item] = {this.checkout.itemsInCart()}

  def subtotal(): Double = {this.checkout.subtotal()}

  def tax(): Double = {this.checkout.tax()}

  def total(): Double ={ this.checkout.total()}
  def prepareStore(): Unit ={}
}

