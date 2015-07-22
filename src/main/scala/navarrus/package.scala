import scala.math.BigDecimal

package object navarrus {

  /**
   * Implicitly convert BigDecimal to Money by using a scoped implicit Currency
   *
   * @param amount the BigDecimal to convert
   * @param currency the implicit currency
   * @return a Money instance
   */
  implicit def bigDecimal2Money(amount: BigDecimal)(implicit currency:Currency) =
    new Money(amount, currency)


  /**
   * Implicitly wraps a BigDecimal into a richer one which provides additional metods
   *
   * @param bigDecimal
   */
  implicit class RichBigDecimal(bigDecimal: BigDecimal) {

    /**
     * Delegates to BigDecimal.pow()
     *
     * @param n
     * @return
     */
    def ^(n: Int) = bigDecimal.pow(n)
  }

}
