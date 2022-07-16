package org.edinar.accounting.entities;

public enum Currency {
    AUD("AUD", "036", 2, "A$", "Australian dollar", false),
    BAM("BAM", "977", 2, "KM", "Bosnia and Herzegovina convertible mark", false),
    CAD("CAD", "124", 2, "C$", "Canadian dollar", false),
    CNY("CNY", "156", 2, "¥", "Chinese yuan", false),
    CZK("CZK", "203", 2, "Kč", "Czech koruna", false),
    DKK("DKK", "208", 2, "kr", "Danish krone", false),
    EUR("EUR", "987", 2, "€", "Euro", false),
    GBP("GBP", "826", 2, "£", "Pound sterling", false),
    HRK("HRK", "191", 2, "kn", "Croatian kuna", false),
    INR("INR", "356", 2, "₹", "Indian rupee", false),
    JPY("JPY", "392", 0, "¥", "Japanese yen", false),
    MKD("MKD", "807", 2, "den", "Macedonian denar", false),
    NOK("NOK", "578", 2, "kr.", "Norwegian krone", false),
    PLN("PLN", "985", 2, "zł", "Polish złoty", false),
    RSD("RSD", "941", 2, "din", "Serbian dinar", false),
    RUB("RUB", "643", 2, "₽", "Russian ruble", false),
    SEK("SEK", "752", 2, "kr", "Swedish krona", false),
    SGD("SGD", "702", 2, "S$", "Singapore dollar", false),
    UAH("UAH", "980", 2, "₴", "Ukranian hryvnia", false),
    USD("USD", "840", 2, "$", "United States dollar", false),
    YUG("YUD", "890", 2, "din", "Yugoslav dinar", true),

    // Experimental:
    BTC("BTC", "000", 8, "฿", "Bitcoin", false),
    ETH("ETH", "000", 18, "Ξ", "Ethereum", false),
    ;

    private final String code;
    private final String number;
    private final Integer exponent;
    private final String symbol;
    private final String name;
    private final Boolean historic;

    /**
     * @param code a three-letter uppercase alphabetic code (see ISO 4217)
     * @param number a three-digit numeric code (see ISO 4217)
     * @param exponent the number of decimal places
     * @param symbol official symbol
     * @param name official name
     * @param historic whether the currency is still in-use
     */
    Currency(String code,
             String number,
             Integer exponent,
             String symbol,
             String name,
             Boolean historic) {
        this.code = code;
        this.number = number;
        this.exponent = exponent;
        this.symbol = symbol;
        this.name = name;
        this.historic = historic;
    }

    public final String getCode() {
        return code;
    }

    public final String getNumber() {
        return number;
    }

    public final int getExponent() {
        return exponent;
    }

    public final String getSymbol() {
        return symbol;
    }

    public final String getName() {
        return name;
    }

    public final Boolean isHistoric() {
        return historic;
    }
}
