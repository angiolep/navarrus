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

  it should "throw exception when operating on different currencies" in {
    Seq (
      (m1: Money, m2: Money) => m1 + m2,
      (m1: Money, m2: Money) => m1 - m2,
      (m1: Money, m2: Money) => m1 + m2,
      (m1: Money, m2: Money) => m1 + m2
    )
    .foreach (op =>
      the [IllegalArgumentException] thrownBy (
        op(Money("100.00"), Money("100.00")(JPY))
      )
      should have message (s"requirement failed: ${Money.CurrencyRequirement}")
    )
  }

  it should "succeed when operating on same currencies" in {
    (Money("100.994") + Money("0.017")) should equal(Money("101.011"))
    (Money("100.994") - Money("0.017")) should equal(Money("100.977"))
    (Money("100.994") * Money("0.017")) should equal(Money("1.716898"))
    (Money("100.994") / Money("0.017")) should equal(Money("5940.82352941176"))
  }

  it should "succeed when operating on BigDecimal" in {
    (Money("100.994") + BigDecimal("0.017")) should equal(Money("101.011"))
    (BigDecimal("0.017") + Money("100.994")) should equal(Money("101.011"))
    (Money("100.994") - BigDecimal("0.017")) should equal(Money("100.977"))
    (BigDecimal("100.994") - Money("0.017")) should equal(Money("100.977"))
    (Money("100.994") * BigDecimal("0.017")) should equal(Money("1.716898"))
    (BigDecimal("100.994") * Money("0.017")) should equal(Money("1.716898"))
    (Money("100.994") / BigDecimal("0.017")) should equal(Money("5940.82352941176"))
    (BigDecimal("100.994") / Money("0.017")) should equal(Money("5940.82352941176"))
  }
}
