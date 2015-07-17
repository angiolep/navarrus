# navarrus
Computational Finance in Scala


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
// pass an explicit currency
val money = Money("100.00")(GBP)

// OR pass an implicit currency (to be preferred)
implicit val USD = Currency("USD")
val money = Money("100.00")
```
> __Warning__   
> An implicit Currency in scope is required by some implicit conversion from BigDecimal to Money, as in BigDecimal("0.03") * Money("100.00")

Money class provides equality which takes currency and rounding into account.

```scala
// true
Money("100.00")(GBP) == Money("99.999")(GBP)

// false
Money("100.00")(GBP) == Money("100.00")(USD)
```

Money class delegates any algebra to the underlying Scala BigDecimal

```scala
implicit val currency = Currency("USD")
val presentValue = Money("100.00")
val interestRate = BigDecimal("0.03") // yearly
val numOfPeriods = 5 // years
val futureValue = presentValue * ((1 + interestRate) ^ numOfPeriods)
```

Finally, Money class rounds its amount according to its currency:

```scala
// $115.93
futureValue.round

// . 99
money("98.997810")(Currency("JPY"))
```
