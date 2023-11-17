package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items.Item


class Task3 extends FunSuite {
    test("repressing enter to put item in cart"){
      val checkout: SelfCheckout = new SelfCheckout()
      val testItem: Item = new Item ("cheese",12.0)
      checkout.addItemToStore("472", testItem)

      checkout.numberPressed(4)
      checkout.numberPressed(7)
      checkout.numberPressed(2)

      checkout.enterPressed()
      checkout.enterPressed()
      checkout.enterPressed()

      val cart:List[Item] = checkout.itemsInCart()
      //assert(cart.size == 3)
      assert(cart.head.description()=="cheese")
      assert(cart(1).description()=="cheese")
      assert(cart(2).description()=="cheese")

      assert(Math.abs(cart.head.price()-12.0)<0.0001)
      assert(Math.abs(cart(1).price()-12.0)<0.0001)
      assert(Math.abs(cart(2).price()-12.0)<0.0001)
      assert(checkout.displayString()== "")
    }

    /*test("credit, loyality, cash bottons have no effects on enter"){
      val checkout: SelfCheckout = new SelfCheckout()
      val testItem: Item = new Item ("cheese",12.0)
      checkout.addItemToStore("472", testItem)

      checkout.numberPressed(4)
      checkout.numberPressed(7)
      checkout.numberPressed(2)

      checkout.enterPressed()
      checkout.loyaltyCardPressed()
      checkout.enterPressed()
      checkout.cashPressed()
      checkout.creditPressed()
      checkout.enterPressed()



      val cart:List[Item] = checkout.itemsInCart()
      assert(cart.size == 3)
      assert(cart.head.description()=="cheese")
      assert(cart(1).description()=="cheese")
      assert(cart(2).description()=="cheese")
      assert(Math.abs(cart.head.price()-12.0)<0.0001)
      assert(Math.abs(cart(1).price()-12.0)<0.0001)
      assert(Math.abs(cart(2).price()-12.0)<0.0001)
      assert(checkout.displayString()== "")
    }
     */

    test("checkout pressed"){
      val checkout: SelfCheckout = new SelfCheckout()
      val testItem: Item = new Item ("cheese",12.0)
      checkout.addItemToStore("472", testItem)

      checkout.numberPressed(4)
      checkout.numberPressed(7)
      checkout.numberPressed(2)

      checkout.enterPressed()
      assert(checkout.displayString()== "")

      val cart:List[Item] = checkout.itemsInCart()
      assert(cart.size == 1)
      assert(cart.head.description()=="cheese")
      assert(Math.abs(cart.head.price()-12.0)<0.0001)

      checkout.checkoutPressed()
      assert(checkout.displayString()== "cash or credit")
    }

    test("cash pressed "){
      val checkout: SelfCheckout = new SelfCheckout()
      val testItem: Item = new Item ("cheese",12.0)
      checkout.addItemToStore("472", testItem)

      checkout.numberPressed(4)
      checkout.numberPressed(7)
      checkout.numberPressed(2)

      checkout.enterPressed()
      assert(checkout.displayString()== "")

      var cart:List[Item] = checkout.itemsInCart()
      assert(cart.size == 1)
      assert(cart.head.description()=="cheese")
      assert(Math.abs(cart.head.price()-12.0)<0.0001)

      assert(cart.size == 1)
      checkout.checkoutPressed()
      assert(checkout.displayString()== "cash or credit")
      assert(cart.size == 1)
      println(cart.size)

      checkout.cashPressed()
      cart = checkout.itemsInCart()
      assert(cart.isEmpty)
      //println(cart.size)
      assert(checkout.displayString()== "")
    }


    test("credit pressed "){
      val checkout: SelfCheckout = new SelfCheckout()
      val testItem: Item = new Item ("cheese",12.0)
      checkout.addItemToStore("472", testItem)

      checkout.numberPressed(4)
      checkout.numberPressed(7)
      checkout.numberPressed(2)

      checkout.enterPressed()
      assert(checkout.displayString()== "")

      var cart:List[Item] = checkout.itemsInCart()
      assert(cart.size == 1)
      assert(cart.head.description()=="cheese")
      assert(Math.abs(cart.head.price()-12.0)<0.0001)

      assert(cart.size == 1)
      checkout.checkoutPressed()
      assert(checkout.displayString()== "cash or credit")
      assert(cart.size == 1)

      checkout.creditPressed()
      cart = checkout.itemsInCart()
      println(cart)
      assert(checkout.displayString()== "")

      /*checkout.enterPressed()
      assert(cart.size == 1)
      assert(cart.head.description()=="error")
      assert(Math.abs(cart.head.price()-0.0)<0.0001)
      assert(checkout.displayString()== "")

       */

    }

}