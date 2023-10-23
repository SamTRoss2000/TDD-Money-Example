import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoneyTest extends TestVariables {


    @Test
    public void testDollarFactoryMethod() {
        assertEquals(MoneyFactory.dollar(5), new Money(5, "USD"));
    }
    @Test
    public void testFrancFactoryMethod() {
        assertEquals(MoneyFactory.franc(5), new Money(5, "CHF"));
    }


    @Test
    public void testDollarMultiplication() {
        assertEquals(Money.dollar(10), MoneyFactory.dollar(5).times(2));
    }
    @Test
    public void testFrancMultiplication() {
        assertEquals(Money.franc(10), MoneyFactory.franc(5).times(2));
    }

    @Test
    public void areDollarObjectsWithIdenticalValuesEqual() {
        assertEquals(Money.dollar(5), MoneyFactory.dollar(5));
        assertNotEquals(Money.dollar(6), MoneyFactory.dollar(5));
    }
    @Test
    public void areFrancObjectsWithIdenticalValuesEqual() {
        assertEquals(Money.franc(5), MoneyFactory.franc(5));
        assertNotEquals(Money.franc(6), MoneyFactory.franc(5));
    }
    @Test
    public void areObjectsWithDifferentCurrenciesInequal() {
        assertNotEquals(MoneyFactory.dollar(5), MoneyFactory.franc(5));
    }

    @Test
    public void doesTheCurrencyMethodReturnCorrectly() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    // Are objects that are casted to different classes but with identical values equal
    @Test
    public void testDifferentClassEquality() {
        assertEquals(new Money(5, "USD"), MoneyFactory.dollar(5));
    }

    // THIS MAY NEED MOVING
    @Test
    public void testAdditionOfMoneyObjects() {
        Expression sum = MoneyFactory.dollar(5).plus(MoneyFactory.dollar(5));
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(MoneyFactory.dollar(10), reduced);
    }

    // does the plus method return a sum with the correct assignments of the inputted Money objects
    @Test
    public void testPlusReturnsSum() {
        Expression result = MoneyFactory.dollar(5).plus(MoneyFactory.dollar(5));
        Sum sum = (Sum) result;
        assertEquals(MoneyFactory.dollar(5), sum.augend);
        assertEquals(MoneyFactory.dollar(5), sum.addend);
    }

}
