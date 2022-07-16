package org.edinar.accounting.entities;

import java.math.BigInteger;

import org.edinar.accounting.AccountingException;

public final class AmountBuilder {
    private Currency currency;
    private BigInteger quantity;

    public final AmountBuilder setCurrency(final Currency currency) {
        this.currency = currency;
        return this;
    }

    public final AmountBuilder setQuantity(final BigInteger quantity) {
        this.quantity = quantity;
        return this;
    }

    public final Amount build() throws AccountingException {
        validateCurrency();
        validateQuantity();
        return new Amount(currency, quantity);
    }

    public void validateCurrency() throws AccountingException {
        if (currency == null) {
            throw new AccountingException("Missing currency: null");
        }
    }

    public void validateQuantity() throws AccountingException {
        if (quantity == null) {
            throw new AccountingException("Missing currency: null");
        }
    }
}
