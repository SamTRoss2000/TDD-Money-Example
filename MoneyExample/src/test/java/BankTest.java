import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest extends TestVariables {


    // Does the reduce method convert a sum to a Money with the correct value
    @Test
    public void reducingSumToCorrectAmount() {
        Money result = bank.reduce(MoneyFactory.doubleExpression(MoneyFactory.dollar(5)), "USD");
        assertEquals(MoneyFactory.dollar(10), result);
    }

    // Does the reduce method not alter the value of a Money object if the currency is the same
    @Test
    public void testReduceMoney() {
        Money result = bank.reduce(MoneyFactory.dollar(3), "USD");
        assertEquals(Money.dollar(3), result);
    }

    // Does convering a franc to a dollar alter the value according to a provided exchange rate
    @Test
    public void convertingCurrenciesWithAnExchangeRate() {
        francToDollarRate();
        Money result = bank.reduce(MoneyFactory.franc(8), "USD");
        assertEquals(MoneyFactory.dollar(4), result);
    }

    // Is an exchange rate of 1 returned if the currencies provided are identical
    @Test
    public void testIdentitcalCurrencyRate() {
        assertEquals(1, new Bank().rate("USD", "USD"));
    }

    // Does using the reduce method on a sum of two different currencies return an amount altered by the exchange rates
    @Test
    public void doMixedCurrencySumsReduceAsExpecteed() {
        francToDollarRate();
        Money result = bank.reduce(MoneyFactory.sumExpression(MoneyFactory.dollar(5), MoneyFactory.franc(10)), "USD"); // could use sumExpression method here
        assertEquals(Money.dollar(10), result);
    }


}
