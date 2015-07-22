package navarrus

import scala.math.BigDecimal
import BigDecimal.RoundingMode.HALF_EVEN


/**
 * Represent money. Money has amount and currency.
 *
 * @param amount the money amount
 * @param currency the money currency
 */
class Money private[navarrus] (val amount:BigDecimal, val currency:Currency) {
  import Money._

  // amount scaled by currency scale and rounded by HALF_EVEN (bankers rounding)
  private val scaled =  amount.setScale(currency.scale, HALF_EVEN)

  /**
   * Round this amount according to its currency (e.g "100.1287" becomes "100.13" when currency is "GBP")
   *
   * @return a new Money instance scaled by its currency scale
   */
  def round = new Money(scaled, this.currency)


  def +(that: Money) = {
    require(that.currency == this.currency, CurrencyRequirement)
    new Money(this.amount + that.amount, this.currency)
  }

  def -(that: Money) = {
    require(that.currency == this.currency, CurrencyRequirement)
    new Money(this.amount - that.amount, this.currency)
  }

  def *(that: Money) =  {
    require(that.currency == this.currency, CurrencyRequirement)
    new Money(this.amount * that.amount, this.currency)
  }

  def /(that: Money) =  {
    require(that.currency == this.currency, CurrencyRequirement)
    new Money(this.amount / that.amount, this.currency)
  }


  /**
   * Compare this money object against the supplied one by currency and rounded amount
   *
   * @param other money object to compare against
   * @return true if monies have same currencies and scaled amount
   */
  override def equals(other:Any) : Boolean = other match {
    case that:Money => this.currency == that.currency && this.scaled == that.scaled
    case _ => false
  }

  override def toString = s"${currency.symbol} ${scaled}"
}



object Money {
  private[navarrus] val CurrencyRequirement: String = "currency must match"

  /**
   * Create a Money instance having the given amount and implicit currency.
   *
   * @param amount the money amount as String (e.g. "100.98")
   * @param currency the implicit money currency
   * @return a Money instance
   */
  def apply(amount:String)(implicit currency:Currency) = new Money(BigDecimal(amount), currency)
}


