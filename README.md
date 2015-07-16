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

