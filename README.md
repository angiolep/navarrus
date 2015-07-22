# navarrus
Computational Finance in Scala

```scala
import navarrus._
```


## Currency
Currency is represented by the ``navarrus.Currency`` class whose instances can be created passing the [ISO 4217](http://www.iso.org/iso/home/standards/currency_codes.htm) currency code. 

```scala
// Currency for your default locale
val DefaultCurrency = Currency()

// British Pound for your default locale
val GBP = Currency("GBP")

// or
val LocalizedBritishPound = Currency("GBP", java.util.Locale.US)
```


### Money
Inspired by [Patterns of Enterprise Application Architecture](http://martinfowler.com/eaaCatalog/money.html), money is represented by the ``navarrus.Money`` class which holds a Scala BigDecimal amount (passed as a literal number) alongside its currency.

You can create Money instances by invoking its curried factory as follows:

```scala
// passing an explicit currency
val money = Money("100.00")(GBP)

// OR passing a scoped implicit currency (to be preferred)
implicit val USD = Currency("USD")
val money = Money("100.00")
```
> __Warning__   
> A unique scoped implicit Currency is required by the implicit conversion ``Money.fromBigDecimal`` so that algebraic operations which accept Money can be also invoked on BigDecimal objects.



Money class provides equality which takes currency and rounding into account.

```scala
Money("100.00")(GBP) == Money("99.999")(GBP)
// res0: Boolean = true

Money("100.00")(GBP) == Money("100.00")(USD)
// res0: Boolean = false
```

Money class delegates any algebra to the underlying Scala BigDecimal

```scala
implicit val currency = Currency("USD")
val presentValue = Money("100.00")
val interestRate = BigDecimal("0.03")
val numOfPeriods = 5
val futureValue = presentValue * ((1 + interestRate) ^ numOfPeriods)
```

Finally, Money class rounds its amount according to its currency:

```scala
// $115.93
futureValue.round

// . 99
money("98.997810")(Currency("JPY"))
```
