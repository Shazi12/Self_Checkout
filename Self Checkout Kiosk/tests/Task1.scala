package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.Item

class Task1 extends FunSuite {

  test("test items description") {
    val testItem: Item = new Item("test item", 100.0)
    assert(testItem.description()=="test item")
  }

  test("test items description(paul)") {
    var descriptions:List[String]=List()
    var prices: List[Double]= List()
    descriptions=descriptions:+"My description"
    prices=prices:+5.99
    descriptions=descriptions:+"Oreos"
    prices= prices:+4.50
    var items: List[Item] = List()
    for (index<-descriptions.indices){
      items= items:+new Item (descriptions(index),prices(index))
      assert(items(index).description()== descriptions(index), "testing description" +
        "expected:"+ descriptions(index)+"actually got:"+ items(index).description())
      assert(Math.abs(items(index).price()-prices(index))<0.001,"testing constructor" + "expected:" +prices(index)+ "got"+items(index).price())
      items(index).setBasePrice(prices(index)-5)
      assert(Math.abs(items(index).price()-(prices(index)-5))<.001,"testing setBasePrice"+"expected:"+(prices(index)-5)+" got: " +items(index).price())
    }
  }

  test("check for cleared"){
    val checkout: SelfCheckout = new SelfCheckout()
    //var testItem: Item = new Item ("cheese",12.0)
    //checkout.addItemToStore("472", testItem)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.clearPressed()
    assert(checkout.displayString()== "")

  }

  test("test pushing number button") {
    val testSelfCheckout: SelfCheckout = new SelfCheckout()
    assert(testSelfCheckout.displayString() == "")
    testSelfCheckout.numberPressed(4)
    assert(testSelfCheckout.displayString() =="4")
    testSelfCheckout.numberPressed(7)
    assert(testSelfCheckout.displayString() =="47")
    testSelfCheckout.numberPressed(2)
    assert(testSelfCheckout.displayString() =="472")
    //    var testItem: Item = new Item("test item", 100.0)
    //    testSelfCheckout.addItemToStore("123", testItem)
    // TODO
  }



  test("buying an item"){
    val checkout: SelfCheckout = new SelfCheckout()
    val testItem: Item = new Item ("cheese",12.0)
    checkout.addItemToStore("472", testItem)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    val cart:List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description()=="cheese")
    assert(Math.abs(cart.head.price()-12.0)<0.0001)
    assert(checkout.displayString()== "")
  }
  test("price_change_doesnt_update_items_in_cart"){
    val checkout: SelfCheckout = new SelfCheckout()
    val testItem: Item = new Item ("cheese",12.0)
    checkout.addItemToStore("472", testItem)
    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    var cart:List[Item] = checkout.itemsInCart()

    testItem.setBasePrice(4.0)
    assert(Math.abs(cart.head.price()-4.0) < 0.001)


  }


  test("multiply of same item"){
    val checkout: SelfCheckout = new SelfCheckout()
    val testItem: Item = new Item ("cheese",12.0)
    checkout.addItemToStore("472", testItem)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()
    // second item
    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)

    checkout.enterPressed()

    val cart:List[Item] = checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="cheese")
    assert(cart(1).description()=="cheese")
    assert(Math.abs(cart.head.price()-12.0)<0.0001)
    assert(Math.abs(cart(1).price()-12.0)<0.0001)
  }

  test("multiply of different item"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item ("cheese",12.0)
    checkout.addItemToStore("472", testItem)
    var testItem2: Item = new Item ("apple",0.89)
    checkout.addItemToStore("012", testItem2)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.numberPressed(2)
    checkout.enterPressed()

    // second item
    checkout.numberPressed(0)
    checkout.numberPressed(1)
    checkout.numberPressed(2)

    checkout.enterPressed()

    val cart:List[Item] = checkout.itemsInCart()
    assert(cart.size == 2)
    assert(cart.head.description()=="cheese")
    assert(cart(1).description()=="apple")
    assert(Math.abs(cart.head.price()-12.0)<0.0001)
    assert(Math.abs(cart(1).price()-0.89)<0.0001)
  }
  test("check invaild test"){
    val checkout: SelfCheckout = new SelfCheckout()
    var testItem: Item = new Item ("cheese",12.0)
    checkout.addItemToStore("472", testItem)

    checkout.numberPressed(4)
    checkout.numberPressed(7)
    checkout.enterPressed()
    val cart:List[Item] = checkout.itemsInCart()
    assert(cart.size == 1)
    assert(cart.head.description() == "error")
    assert(Math.abs(cart.head.price()-0.0)<0.0001)


  }


}

