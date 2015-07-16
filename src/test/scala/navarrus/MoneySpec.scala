package navarrus

import org.scalatest.{ShouldMatchers, FlatSpec}


class MoneySpec extends FlatSpec with ShouldMatchers {

  implicit val GBP = Currency("GBP")
  val JPY = Currency("JPY")

  "Money" should  "compare by equality" in {
    Money("100.00") should equal(Money("100.00"))
    Money("100.00") should not equal(Money("100.00")(JPY))
  }
  it should "round according to its currency" in {
    Money("100.994").round should equal (Money("100.99"))
    Money("100.994")(JPY).round should equal (Money("101")(JPY))
  }
  it should "fail when adding different currency" in {
    an [IllegalArgumentException ] should be thrownBy(Money("100.00") + Money("100.00")(JPY))
  }
  it should "fail when subtracting different currency" in {
    an [IllegalArgumentException ] should be thrownBy(Money("100.00") - Money("100.00")(JPY))
  }
  it should "succeed when adding same currency" in {
    (Money("100.994") + Money("0.017")) should equal(Money("101.011"))
  }
  it should "succeed when subtracting same currency" in {
    (Money("100.994") - Money("0.017")) should equal(Money("100.977"))
  }
  /*TODO it should "succeed when multiplying a BigDecimal" in {
    (Money("100.00") * BigDecimal("0.02")) should equal(Money("2.00"))
    (BigDecimal("0.02") * Money("100.00")) should equal(Money("2.00"))
  }*/
}
