import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class ExpressionTest extends TestVariables {


    // Can a sum object have another money object added to it and reduce to the expected amount
    @Test
    public void canAMoneyBeAddedToASum() {
        francToDollarRate();
        Expression plusSum = MoneyFactory.doubleExpression(MoneyFactory.dollar(5)).plus(MoneyFactory.dollar(5));
        Money result = bank.reduce(plusSum, "USD");
        assertEquals(result, Money.dollar(15));
    }

    // Does the times method multiply both values stored within a Sum object
    @Test
    public void canASumBeMultiplied() {
        francToDollarRate();
        Expression timesSum = MoneyFactory.doubleExpression(MoneyFactory.dollar(5)).times(2);
        Money result = bank.reduce(timesSum, "USD");
        assertEquals(Money.dollar(20), result);
    }

    // does the currency method in the expression class return the correct currency
    @Test
    public void testCurrencyMethodOnExpression() {
        Sum sum1 = MoneyFactory.sumExpression(MoneyFactory.dollar(5), MoneyFactory.franc(5));
        Sum sum2 = MoneyFactory.sumExpression(MoneyFactory.franc(5), MoneyFactory.dollar(5));
        assertEquals(sum1.addend.currency(), "CHF");
        assertEquals(sum1.augend.currency(), "USD");
        assertNotEquals(sum1.augend.currency(), sum1.addend.currency());
        assertEquals(sum1.augend.currency(), sum2.addend.currency());
    }
}
