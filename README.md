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


Money class rounds its amount according to its currency:

```scala
Money("115.9288912")(Currency("USD")).round
// -> navarrus.Money = $ 115.93

Money("98.997810")(Currency("JPY")).round
// -> navarrus.Money = JPY 99

```


Money class provides equality which takes currency and rounding into account.

```scala
Money("100.00")(GBP) == Money("99.999")(GBP)
// -> Boolean = true

Money("100.00")(GBP) == Money("100.00")(USD)
// -> Boolean = false
```


Money provides algebraic operations

```scala
Money("100.994") + Money("0.017")
// -> navarrus.Money = $101.01

Money("100.994") / Money("0.017")
// -> navarrus.Money = $5940.82
```

> __Warning__   
> A unique scoped implicit Currency is required by the implicit conversion ``navarrus.bigDecimal2Money`` so that algebraic operations can be also invoked on BigDecimal objects.


```scala
val presentValue = Money("100.00")
val interestRate = BigDecimal("0.03")
val numOfPeriods = 5
val futureValue = presentValue * ((1 + interestRate) ^ numOfPeriods)
// -> navarrus.Money = $ 115.93
```

