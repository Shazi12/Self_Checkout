package store.model.checkout

import store.model.items.Item

// Class that has a variable named state and has has each method in the abstract class
//*****************************************************************************************8
class SelfCheckout{
  var code: String = ""
  var map: Map[String, Item] = Map() // create a state variable that maps the barcode to the item
  var shoppingcart: List[Item]= List()
  val error: Item = new Item("error", 0.0)

  var state: CheckoutState = new RegSelfCheckout(this)


  def addItemToStore(barcode: String, item: Item): Unit = {
    this.state.addItemToStore(barcode,item)}

  def numberPressed(number: Int): Unit = {
    this.state.numberPressed(number)}

  def clearPressed(): Unit = {
    this.state.clearPressed()}

  def enterPressed(): Unit = {
    this.state.enterPressed()}

  def checkoutPressed(): Unit = {
    this.state.checkoutPressed()}

  def cashPressed(): Unit = {
    this.state.cashPressed()}

  def creditPressed(): Unit = {
    this.state.creditPressed()}

  def loyaltyCardPressed(): Unit = {
    this.state.loyaltyCardPressed()}

  def displayString(): String = {
    this.state.displayString()}

  def itemsInCart(): List[Item] = {
    this.state.itemsInCart()}

  def subtotal(): Double = {
    this.state.subtotal()}


  def tax(): Double = {
    this.state.tax()}

  def total(): Double = {
    this.state.total()}

  def prepareStore(): Unit = {
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



