package org.edinar.accounting.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;

public final class Amount {
    private final Currency currency;
    private final BigInteger quantity;

    /**
     * Package-private access, use {@link AmountBuilder}
     * @param currency see {@link Currency}
     * @param quantity the decimal value multiplied by the currency's exponent
     */
    Amount(final Currency currency, final BigInteger quantity) {
        this.currency = currency;
        this.quantity = quantity;
    }

    public final Currency getCurrency() {
        return currency;
    }

    public final BigInteger getQuantity() {
        return quantity;
    }

    public final BigDecimal getQuantityAsDecimal() {
        return new BigDecimal(quantity).movePointLeft(currency.getExponent());
    }

    /**
     * Does not alter {@code this} object.
     * @return a new amount object with the same currency but negated value.
     */
    public final Amount negate() {
        return new Amount(currency, quantity.negate());
    }

    /**
     * @return {@code true} iff quantity is zero, otherwise {@code false}
     */
    public final boolean isZero() {
        return BigInteger.ZERO.equals(quantity);
    }

    /**
     * @return {@code true} iff quantity is less than zero, otherwise {@code false}
     */
    public final boolean isNegative() {
        return quantity.compareTo(BigInteger.ZERO) < 0;
    }

    /**
     * @return {@code true} iff quantity is greater than zero, otherwise {@code false}
     */
    public final boolean isPositive() {
        return quantity.compareTo(BigInteger.ZERO) > 0;
    }

    @Override
    public String toString() {
        final DecimalFormat decimalFormat = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol(currency.getSymbol());
        decimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        decimalFormat.setMaximumFractionDigits(currency.getExponent());
        decimalFormat.setMinimumFractionDigits(currency.getExponent());
        return decimalFormat.format(getQuantityAsDecimal());
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (this.getClass() != that.getClass()) {
            return false;
        }
        if (isZero() && ((Amount) that).isZero()) {
            return true;
        }
        if (! Objects.equals(this.quantity, ((Amount) that).quantity)) {
            return false;
        }
        if (this.currency != ((Amount) that).currency) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, quantity);
    }
}
