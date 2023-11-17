package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.{BottleDeposit, Item, Modifier, Sale, SalesTax}


class Task2 extends FunSuite{
  val epilon:Double=0.001
  def compareDouble(d1:Double,d2:Double): Boolean ={
    Math.abs(d1-d2)<epilon
  }
  test("items_wrong_with_no_modifiers"){
    val checkout:SelfCheckout = new SelfCheckout()
    val item: Item = new Item("pineapple",3.5)

    checkout.addItemToStore("283", item)

    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    val cart:List[Item]= checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="pineapple")
    assert(compareDouble(cart.head.price(),3.5))
    assert(cart(1).description()=="pineapple")
    assert(compareDouble(cart(1).price(),3.5))
    assert(compareDouble(checkout.subtotal(),7),"testing description" +
      "expected:"+ 3.5 +"actually got:"+ checkout.subtotal())
    assert(compareDouble(checkout.tax(),0.0),"testing tax" +
      " expected: "+ 0.0 +" actually got: "+ checkout.tax())
    assert(compareDouble(checkout.total(),7),"testing total" +
      " expected: "+ 7 +" actually got: "+ checkout.total())

  }
  test("buying an item with Sale"){
    val checkout:SelfCheckout = new SelfCheckout()
    val item: Item = new Item("pineapple",3.5)
    val sale:Modifier = new Sale(50)
    item.addModifier(sale)

    checkout.addItemToStore("283", item)

    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    val cart:List[Item]= checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="pineapple")
    assert(compareDouble(cart.head.price(),1.75))
    assert(cart(1).description()=="pineapple")
    assert(compareDouble(cart(1).price(),1.75))
    assert(compareDouble(checkout.subtotal(),3.5),"testing description" +
      "expected:"+ 3.5 +"actually got:"+ checkout.subtotal())

  }
  test("buying an item with multiple Sale"){
    val checkout:SelfCheckout = new SelfCheckout()
    val item: Item = new Item("pineapple",3.5)
    val sale:Modifier = new Sale(50)
    val sale2: Modifier = new Sale(20)
    item.addModifier(sale)
    item.addModifier(sale2)
    checkout.addItemToStore("283", item)

    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    val cart:List[Item]= checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="pineapple")
    assert(compareDouble(cart.head.price(),1.4))
    assert(cart(1).description()=="pineapple")
    assert(compareDouble(cart(1).price(),1.4))
    assert(compareDouble(checkout.subtotal(),2.8),"testing description" +
      "expected:"+ 2.8 +"actually got:"+ checkout.subtotal())

  }
  test("buying an item with bottle Deposit"){
    val checkout:SelfCheckout = new SelfCheckout()
    val item: Item = new Item("pineapple",3.5)
    val sale:Modifier = new Sale(50)
    val sale2: Modifier = new Sale(20)
    val deposit: Modifier = new BottleDeposit(.25)
    item.addModifier(sale)
    item.addModifier(sale2)
    item.addModifier(deposit)

    checkout.addItemToStore("283", item)

    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    val cart:List[Item]= checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="pineapple")
    assert(compareDouble(cart.head.price(),1.4))
    assert(cart(1).description()=="pineapple")
    assert(compareDouble(cart(1).price(),1.4))
    assert(compareDouble(checkout.subtotal(),2.8),"testing description" +
      "expected:"+ 3.5 +"actually got:"+ checkout.subtotal())
    assert(compareDouble(checkout.tax(),0.5),"testing tax" +
      " expected: "+ 0.5 +" actually got: "+ checkout.tax())
    assert(compareDouble(checkout.total(),3.3),"testing total" +
      " expected: "+ 4.32 +" actually got: "+ checkout.total())

  }
  test("tax item with multiple Purchase"){
    val checkout:SelfCheckout = new SelfCheckout()
    val item: Item = new Item("pineapple",3.5)
    val tax:Modifier = new SalesTax(8)
    item.addModifier(tax)
    checkout.addItemToStore("283", item)

    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    val cart:List[Item]= checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="pineapple")
    assert(compareDouble(cart.head.price(),3.5))
    assert(cart(1).description()=="pineapple")
    assert(compareDouble(cart(1).price(),3.5))
    assert(compareDouble(checkout.subtotal(),7),"testing description" +
      "expected:"+ 7 +"actually got:"+ checkout.tax())
    assert(compareDouble(checkout.tax(),0.56),"testing description" +
      "expected:"+ 0.56 +"actually got:"+ checkout.tax())
  }
  test("buying an item with multiple Sale, Tax, Deposit Total"){
    val checkout:SelfCheckout = new SelfCheckout()
    val item: Item = new Item("pineapple",5)
    val sale:Modifier = new Sale(50)
    val sale2: Modifier = new Sale(20)
    val tax:Modifier = new SalesTax(8)
    val deposit: Modifier = new BottleDeposit(.25)

    item.addModifier(sale)
    item.addModifier(sale2)
    item.addModifier(tax)
    item.addModifier(deposit)
    checkout.addItemToStore("283", item)

    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    val cart:List[Item]= checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="pineapple")
    assert(compareDouble(cart.head.price(),2))
    assert(cart(1).description()=="pineapple")
    assert(compareDouble(cart(1).price(),2))
    assert(compareDouble(checkout.subtotal(),4),"testing subtotal" +
      " expected: "+ 4 +" actually got: "+ checkout.subtotal())
    assert(compareDouble(checkout.tax(),0.82),"testing tax" + //tax + Bottle deposit == 0.83
      " expected: "+ 0.82 +" actually got: "+ checkout.tax())
    assert(compareDouble(checkout.total(),4.82),"testing total" +
      " expected: "+ 4.82 +" actually got: "+ checkout.total())
  }
  test("buying an item with Sale, Tax, Deposit Total"){
    val checkout:SelfCheckout = new SelfCheckout()
    val item: Item = new Item("pineapple",5)
    val sale:Modifier = new Sale(50)
    val tax:Modifier = new SalesTax(8)
    val deposit: Modifier = new BottleDeposit(.25)

    item.addModifier(sale)

    item.addModifier(tax)
    item.addModifier(deposit)
    checkout.addItemToStore("283", item)

    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    checkout.numberPressed(2)
    checkout.numberPressed(8)
    checkout.numberPressed(3)
    checkout.enterPressed()
    val cart:List[Item]= checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="pineapple")
    assert(compareDouble(cart.head.price(),2.50))
    assert(cart(1).description()=="pineapple")
    assert(compareDouble(cart(1).price(),2.50))
    assert(compareDouble(checkout.subtotal(),5),"testing subtotal" +
      " expected: "+ 4 +" actually got: "+ checkout.subtotal())
    assert(compareDouble(checkout.tax(),0.90),"testing tax" + //tax + Bottle deposit == 0.83
      " expected: "+ 0.92 +" actually got: "+ checkout.tax())
    assert(compareDouble(checkout.total(),5.9),"testing total" +
      " expected: "+ 5.9 +" actually got: "+ checkout.total())
  }

}
