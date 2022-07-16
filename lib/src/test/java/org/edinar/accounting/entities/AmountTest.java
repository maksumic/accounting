package org.edinar.accounting.entities;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.edinar.accounting.AccountingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AmountTest {
    @Test
    public void testGetQuantityAsDecimal() {
        Assertions.assertEquals(new BigDecimal("1001"), new Amount(Currency.JPY, new BigInteger("1001")).getQuantityAsDecimal(), "JPY does not have any decimal places.");
        Assertions.assertEquals(new BigDecimal("10.01"), new Amount(Currency.EUR, new BigInteger("1001")).getQuantityAsDecimal(), "EUR has two decimal places.");
        Assertions.assertEquals(new BigDecimal("0.000000000000001001"), new Amount(Currency.ETH, new BigInteger("1001")).getQuantityAsDecimal(), "ETH has eighteen decimal places.");
        Assertions.assertEquals(new BigDecimal("0.00001001"), new Amount(Currency.BTC, new BigInteger("1001")).getQuantityAsDecimal(), "BTC has eight decimal places.");        
    }

    @Test
    public void testNegate() {
        Amount amount = new Amount(Currency.CZK, new BigInteger("1000"));
        Assertions.assertEquals(new Amount(Currency.CZK, new BigInteger("-1000")), amount.negate());
        Assertions.assertEquals(new Amount(Currency.CZK, new BigInteger("1000")), amount, "The original amount was unexpectedly modified.");
    }

    @Test
    public void testIsZero() {
        Assertions.assertTrue(new Amount(Currency.PLN, new BigInteger("0")).isZero());
        Assertions.assertFalse(new Amount(Currency.PLN, new BigInteger("1")).isZero());
        Assertions.assertFalse(new Amount(Currency.PLN, new BigInteger("-1")).isZero());
    }

    @Test
    public void testIsNegative() {
        Assertions.assertTrue(new Amount(Currency.RUB, new BigInteger("-1")).isNegative());
        Assertions.assertFalse(new Amount(Currency.RUB, new BigInteger("0")).isNegative());
        Assertions.assertFalse(new Amount(Currency.RUB, new BigInteger("1")).isNegative());
    }

    @Test
    public void testIsPositive() {
        Assertions.assertTrue(new Amount(Currency.SEK, new BigInteger("1")).isPositive());
        Assertions.assertFalse(new Amount(Currency.SEK, new BigInteger("0")).isPositive());
        Assertions.assertFalse(new Amount(Currency.SEK, new BigInteger("-1")).isPositive());
    }

    @Test
    public void testToString() throws AccountingException {
        final Map<String, String> negative = Collections.unmodifiableMap(Map.of(
            new Amount(Currency.EUR, new BigInteger("-12300000000050")).toString(), "-€123,000,000,000.50",
            new Amount(Currency.EUR, new BigInteger("-1230000000049")).toString(), "-€12,300,000,000.49",
            new Amount(Currency.EUR, new BigInteger("-123000000048")).toString(), "-€1,230,000,000.48",
            new Amount(Currency.EUR, new BigInteger("-12300000047")).toString(), "-€123,000,000.47",
            new Amount(Currency.EUR, new BigInteger("-1230000046")).toString(), "-€12,300,000.46",
            new Amount(Currency.EUR, new BigInteger("-123000045")).toString(), "-€1,230,000.45",
            new Amount(Currency.EUR, new BigInteger("-569999")).toString(), "-€5,699.99",
            new Amount(Currency.EUR, new BigInteger("-49")).toString(), "-€0.49",
            new Amount(Currency.EUR, new BigInteger("-2")).toString(), "-€0.02",
            new Amount(Currency.CNY, new BigInteger("-0")).toString(), "¥0.00"));
        final Map<String, String> positive = Collections.unmodifiableMap(Map.of(
            new Amount(Currency.EUR, new BigInteger("7")).toString(), "€0.07",
            new Amount(Currency.USD, new BigInteger("25")).toString(), "$0.25",
            new Amount(Currency.USD, new BigInteger("200000")).toString(), "$2,000.00",
            new Amount(Currency.USD, new BigInteger("1010000")).toString(), "$10,100.00",
            new Amount(Currency.USD, new BigInteger("10001000")).toString(), "$100,010.00",
            new Amount(Currency.USD, new BigInteger("100000100")).toString(), "$1,000,001.00",
            new Amount(Currency.USD, new BigInteger("1000000010")).toString(), "$10,000,000.10",
            new Amount(Currency.USD, new BigInteger("10000000001")).toString(), "$100,000,000.01",
            new Amount(Currency.RUB, new BigInteger("100000000000")).toString(), "₽1,000,000,000.00"));
       final Map<String, String> decimals = Collections.unmodifiableMap(Map.of(
            new Amount(Currency.JPY, new BigInteger("80000000123000")).toString(), "¥80,000,000,123,000",
            new Amount(Currency.JPY, new BigInteger("-80000")).toString(), "-¥80,000",
            new Amount(Currency.JPY, new BigInteger("0")).toString(), "¥0",
            new Amount(Currency.ETH, new BigInteger("1")).toString(), "Ξ0.000000000000000001",
            new Amount(Currency.ETH, new BigInteger("-10")).toString(), "-Ξ0.000000000000000010",
            new Amount(Currency.BTC, new BigInteger("2100000087654321")).toString(), "฿21,000,000.87654321"));
        final Map<String, String> tests = new HashMap<>();;
        tests.putAll(negative);
        tests.putAll(positive);
        tests.putAll(decimals);
        for (final Map.Entry<String, String> test : tests.entrySet()) {
            final String expected = test.getValue();
            final String got = test.getKey().toString();
            Assertions.assertEquals(expected, got);
        }
    }

    @Test
    public void testEquals() {
        Assertions.assertEquals(new Amount(Currency.BTC, new BigInteger("0")), new Amount(Currency.ETH, new BigInteger("0")), "Zero-amounts are always equal, even when currencies differ.");
        Assertions.assertEquals(new Amount(Currency.EUR, new BigInteger("1000")), new Amount(Currency.EUR, new BigInteger("1000")));
        Assertions.assertNotEquals(new Amount(Currency.USD, new BigInteger("1000")), new Amount(Currency.EUR, new BigInteger("1000")));
        Assertions.assertNotEquals(new Amount(Currency.USD, new BigInteger("1000")), new Amount(Currency.USD, new BigInteger("100")));
    }
}
