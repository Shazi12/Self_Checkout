package tests

import org.scalatest.FunSuite
import store.model.checkout.SelfCheckout
import store.model.items._

class ApplicationObjective extends FunSuite {

  test("1 - loyalties applied") {
    val testSelfCheckout: SelfCheckout = new SelfCheckout()
    //
    val apple: Item = new Item("apple", 2.00)
    val banana: Item = new Item("banana", 1.00)
    val blueberry: Item = new Item("blueberry", 3.00)
    val soda: Item = new Item("soda", 2.00)
    //
    testSelfCheckout.addItemToStore("01110", apple)
    testSelfCheckout.addItemToStore("101010", banana)
    testSelfCheckout.addItemToStore("1100", blueberry)
    testSelfCheckout.addItemToStore("1010", soda)
    //
    assert(Math.abs(apple.price() - 2.00) < 0.0001, "test 1") // checks to see price has not changed b4 modifiers.
///////////////////////////////////////////// APPLY ALL SALES //////////////////////////////////////////////
    val applesale: Sale = new Sale (20.0)
    val loyaltyApple: LoyaltySale = new LoyaltySale(10.0)
    val appletax: SalesTax = new SalesTax(10.0)
    apple.addModifier(loyaltyApple)
    apple.addModifier(applesale)                                // adds modifiers to the list.
    apple.addModifier(appletax)
    //
    val sodadep: BottleDeposit = new BottleDeposit(0.20)
    soda.addModifier(sodadep)
    //
    val bananasale: Sale = new Sale (10.0)
    val bananatax: SalesTax = new SalesTax(10.0)
    banana.addModifier(bananasale)
    banana.addModifier (bananatax)
    //
    val bluesale: Sale = new Sale(10.0)
    val loyalbb: LoyaltySale = new LoyaltySale(50.0)
    blueberry.addModifier(loyalbb)
    blueberry.addModifier(bluesale)
///////////////////////////////////////////////////////////////////////////////////////////////////////////
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // apple is added to the cart! (index 0)
    testSelfCheckout.enterPressed() // 2 apples should be in the cart. (index 1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // soda is added to the cart! (index 2)

    val appleincart = testSelfCheckout.itemsInCart()(0)
    assert(Math.abs(appleincart.price() - 1.6) < 0.0001, "test 2:" + "I got " + appleincart.price() + " and the expected is 1.6!")

    testSelfCheckout.loyaltyCardPressed() // NEW CHANGE!

    assert(Math.abs(appleincart.price() - 1.44) < 0.0001, "test 3:" + "I got " + appleincart.price() + " and the expected is 1.44!")

    ///////////////////////////////////
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // banana added to the cart! (index 3)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // blueberry added to the cart! (index 4)
    testSelfCheckout.loyaltyCardPressed() // checks it can be pressed multiple times.
    val bananaincart = testSelfCheckout.itemsInCart()(3)
    val bbincart = testSelfCheckout.itemsInCart()(4)

    assert(Math.abs(bananaincart.price() - 0.9) < 0.0001, "test 4:" + "I got " + bananaincart.price() + " and the expected is 0.9!")
    assert(Math.abs(bbincart.price() - 1.35) < 0.0001, "test 5: " + "I got " + bbincart.price() + " and the expected is 1.35!")
// apple: 1.44 x 2 = 2.88 soda: 2.20 banana: 0.99 blueberry: 1.35
    assert (Math.abs(testSelfCheckout.total() - 7.708) < 0.0001,"test 6: " + "I got " + testSelfCheckout.total() + " and the expected is 7.708!")
    /////
   testSelfCheckout.checkoutPressed() // RIGHT HERE IS WHERE THE ERROR OCCURS.
   testSelfCheckout.creditPressed()
    ///////////////////////////////////////////////////////////////////////////
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // adds apple!
//
    val newappleincart = testSelfCheckout.itemsInCart()(0)
    assert(Math.abs(newappleincart.price() - 1.6) < 0.0001, "test 7:" + "I got " + newappleincart.price() + " and the expected is 1.6!")

  }

  test("2 - no loyalty card") {
    val testSelfCheckout: SelfCheckout = new SelfCheckout()
    //
    val apple: Item = new Item("apple", 2.00)
    val banana: Item = new Item("banana", 1.00)
    val blueberry: Item = new Item("blueberry", 3.00)
    val soda: Item = new Item("soda", 2.00)
    //
    testSelfCheckout.addItemToStore("01110", apple)
    testSelfCheckout.addItemToStore("101010", banana)
    testSelfCheckout.addItemToStore("1100", blueberry)
    testSelfCheckout.addItemToStore("1010", soda)
    //
    assert(Math.abs(apple.price() - 2.00) < 0.0001, "test 1") // checks to see price has not changed b4 modifiers.
    ///////////////////////////////////////////// APPLY ALL SALES //////////////////////////////////////////////
    val applesale: Sale = new Sale (20.0)
    val loyaltyApple: LoyaltySale = new LoyaltySale(10.0)
    val appletax: SalesTax = new SalesTax(10.0)
    apple.addModifier(applesale)                                // adds modifiers to the list.
    apple.addModifier(loyaltyApple)
    apple.addModifier(appletax)
    //
    val sodadep: BottleDeposit = new BottleDeposit(0.20)
    soda.addModifier(sodadep)
    //
    val bananasale: Sale = new Sale (10.0)
    val bananatax: SalesTax = new SalesTax(10.0)
    banana.addModifier(bananasale)
    banana.addModifier (bananatax)
    //
    val loyalbb: LoyaltySale = new LoyaltySale(10.0)
    blueberry.addModifier(loyalbb)
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // apple is added to the cart!
    testSelfCheckout.enterPressed() // 2 apples should be in the cart.
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // soda is added to the cart!
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // banana added to the cart!
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // blueberry added to the cart!


    val appleincart = testSelfCheckout.itemsInCart()(0)
    val sodaincart = testSelfCheckout.itemsInCart()(2)
    val bananaincart = testSelfCheckout.itemsInCart()(3)
    val bbincart = testSelfCheckout.itemsInCart()(4)
    assert(Math.abs(appleincart.price() - 1.6) < 0.0001, "test 2:" + "I got" + appleincart.price() + "and the expected is 1.6!")
// checks to make sure loyaltysale is not being applied.
    assert(Math.abs(sodaincart.price() - 2.0) < 0.0001, "test 3:" + "I got" + sodaincart.price() + "and the expected is 2.0!")
    assert(Math.abs(bananaincart.price() - 0.9) < 0.0001, "test 4:" + "I got" + bananaincart.price() + "and the expected is 0.9!")
    assert(Math.abs(bbincart.price() - 3.0) < 0.0001, "test 5:" + "I got" + bbincart.price() + "and the expected is 3.0!")
//  checks to make sure loyaltysale is not being applied.
    assert(Math.abs(testSelfCheckout.total() - 9.71) < 0.0001, "test 6:" + "I got" + testSelfCheckout.total() + "and the expected is 9.71!")

  }

  test("3 - loyalties applied at checkout") {
    val testSelfCheckout: SelfCheckout = new SelfCheckout()
    //
    val apple: Item = new Item("apple", 2.00)
    val banana: Item = new Item("banana", 1.00)
    val blueberry: Item = new Item("blueberry", 3.00)
    val soda: Item = new Item("soda", 2.00)
    //
    testSelfCheckout.addItemToStore("01110", apple)
    testSelfCheckout.addItemToStore("101010", banana)
    testSelfCheckout.addItemToStore("110010111", blueberry)
    testSelfCheckout.addItemToStore("1010", soda)
    //
    assert(Math.abs(apple.price() - 2.00) < 0.0001, "test 1") // checks to see price has not changed b4 modifiers.
    ///////////////////////////////////////////// APPLY ALL SALES //////////////////////////////////////////////
    val applesale: Sale = new Sale (20.0)
    val loyaltyApple: LoyaltySale = new LoyaltySale(10.0)
    val appletax: SalesTax = new SalesTax(10.0)
    apple.addModifier(applesale)                                // adds modifiers to the list.
    apple.addModifier(loyaltyApple)
    apple.addModifier(appletax)
    //
    val sodadep: BottleDeposit = new BottleDeposit(0.20)
    soda.addModifier(sodadep)
    //
    val bananasale: Sale = new Sale (10.0)
    val bananatax: SalesTax = new SalesTax(10.0)
    banana.addModifier(bananasale)
    banana.addModifier (bananatax)
    //
    val loyalbb: LoyaltySale = new LoyaltySale(50.0)
    blueberry.addModifier(loyalbb)
/////////////////////////////////////////////////////////////////////////////////////
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // apple is added to the cart! (index 0)
    testSelfCheckout.enterPressed() // 2 apples should be in the cart. (index 1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // soda is added to the cart! (index 2)

    val appleincart = testSelfCheckout.itemsInCart()(0)
    assert(Math.abs(appleincart.price() - 1.6) < 0.0001, "test 2:" + "I got " + appleincart.price() + " and the expected is 1.6!")



    ///////////////////////////////////
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // banana added to the cart! (index 3)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(1)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.numberPressed(0)
    testSelfCheckout.enterPressed() // blueberry added to the cart! (index 4)

    val bananaincart = testSelfCheckout.itemsInCart()(3)
    val bbincart = testSelfCheckout.itemsInCart()(4)
    assert(Math.abs(appleincart.price() - 1.6) < 0.0001, "test 777:" + "I got " + appleincart.price() + " and the expected is 1.6!")
    testSelfCheckout.checkoutPressed()
    assert(Math.abs(appleincart.price() - 1.6) < 0.0001, "test 666:" + "I got " + appleincart.price() + " and the expected is 1.6!")
    testSelfCheckout.loyaltyCardPressed()
    assert(Math.abs(appleincart.price() - 1.44) < 0.0001, "test 3:" + "I got " + appleincart.price() + " and the expected is 1.44!")
    assert(Math.abs(bananaincart.price() - 0.9) < 0.0001, "test 4:" + "I got " + bananaincart.price() + " and the expected is 0.9!")
    // assert(Math.abs(bbincart.price() - 1.35) < 0.0001, "test 5: " + "I got " + bbincart.price() + " and the expected is 1.35!")
    //assert(Math.abs(testSelfCheckout.total() - 7.708) < 0.0001,"test 6: " + "I got " + testSelfCheckout.total() + " and the expected is 7.708!")
    /////


  }


}
