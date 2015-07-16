package navarrus

/**
 * Represents a currency. Currencies are identified by their ISO 4217 currency codes.
 *
 * @param code the ISO 4217 code of the currency
 * @param symbol the symbol of this currency (e.g. for the US Dollar, the symbol is "$")
 * @param scale the default number of fraction digits used with this currency
 */
class Currency private (val code:String, val symbol:String, val scale:Int) {

  override def equals(other: Any) = other match {
    case that:Currency => this.code == that.code
    case _ => false
  }

  override def toString = s"[$code, $symbol, $scale]"
}

object Currency {

  private[this] val defaultLocale = java.util.Locale.getDefault()

  private[this] val defaultCurrencyCode = java.util.Currency.getInstance(defaultLocale).getCurrencyCode

  /**
   * Create a Currency instance having the supplied currency code
   *
   * @param code the ISO 4217 currency code
   * @return a Currency instance
   */
  def apply(code:String = defaultCurrencyCode, locale: java.util.Locale = defaultLocale) = {
    val jc = java.util.Currency.getInstance(code)
    new Currency(
      jc.getCurrencyCode,
      // symbol is local-sensitive (e.g. it is "[GBP]"  when locale is "US_*")
      jc.getSymbol(locale),
      jc.getDefaultFractionDigits
    )
  }
}


