package org.edinar.accounting.entities;

import java.math.BigInteger;

import org.edinar.accounting.AccountingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AmountBuilderTest {
    @Test
    public void testBuild() throws AccountingException {
        Currency currency = Currency.AUD;
        BigInteger quantity = new BigInteger("4997");
        Amount amount = new AmountBuilder()
            .setCurrency(currency)
            .setQuantity(quantity)
            .build();
        Assertions.assertNotNull(amount);
        Assertions.assertEquals(currency, amount.getCurrency());
        Assertions.assertEquals(quantity, amount.getQuantity());
    }

    @Test
    public void testBuild_throwIfMissingCurrency() {
        Assertions.assertThrows(
            AccountingException.class,
            () -> new AmountBuilder()
                .setCurrency(null)
                .setQuantity(new BigInteger("1000"))
                .build());
    }

    @Test
    public void testBuild_throwIfMissingQuantity() {
        Assertions.assertThrows(
            AccountingException.class,
            () -> new AmountBuilder()
                .setCurrency(Currency.BAM)
                .setQuantity(null)
                .build());
    }
}
