package navarrus

import java.util.Locale
import org.scalatest.{FlatSpec, ShouldMatchers}


class CurrencySpec extends FlatSpec with ShouldMatchers {

  "A Currency"  should "be properly localized in UK" in {
    val pound = Currency("GBP", Locale.UK)
    pound.code shouldBe "GBP"
    pound.symbol shouldBe "\u00A3"
    pound.scale shouldBe 2
  }

  it should  "be properly localized in US" in {
    val dollar = Currency("USD", Locale.US)
    dollar.code shouldBe "USD"
    dollar.symbol shouldBe "\u0024"
    dollar.scale shouldBe 2
  }

  it should  "be properly localized in Japan" in {
    val yen = Currency("JPY", Locale.JAPAN)
    yen.code shouldBe "JPY"
    // see http://www.fileformat.info/info/unicode/char/ffe5/index.htm
    yen.symbol shouldBe "\uFFE5"
    yen.scale shouldBe 0
  }
}

