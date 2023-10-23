import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

// toString returns the amount and currency of the addend and augend
// toString is null safe

// times multiplies the augend and addend of a sum
// what happens if times is multiplied by 0 (edge cases)
// what happens if times is multiplied by a negative number
class SumTest {

    private static Money createFiveFranc() {
        return Money.franc(5);
    }
    private static Money createFourFranc() {
        return Money.franc(4);
    }

    Sum createFiveFiveFrancSum = new Sum(createFiveFranc(), createFiveFranc());
    Sum createFiveFourFrancSum = new Sum(createFiveFranc(), createFourFranc());

    //Sum c = new Sum(createFourFranc(), createFiveFranc());

    @Test
    void isNullAugend() {
        try {
            new Sum(null, createFiveFranc());
            failTest();
        }
        catch(IllegalArgumentException e) {
            System.out.println(e);
            assertEquals(e.getMessage(), "Augend cannot be null");
        }
    }

    private static void failTest() {
        Assertions.fail("Expected an error here");
    }

    @Test
    void isNullAddend() {
        try {
            new Sum(createFiveFranc(), null);
            failTest();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e);
            assertEquals(e.getMessage(), "Addend cannot be null");
        }
    }


    @Test
    void isNullAugendAndAddend() {
        Assertions.fail(); // look at fixing this
        try {
            new Sum(null, null);
            failTest();
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void multiplyAugendOrAddendExpression() {
        Expression tenFranc = new Money(10, "CHF");
        assertEquals(createFiveFiveFrancSum.augend.times(2), tenFranc);
        assertEquals(createFiveFiveFrancSum.addend.times(2), tenFranc);
    }

    // returning a new Sum where both the augend & addend are multiplied by an amount
    @Test
    public void multiplyAugendAndAddendOfSum() {
        Sum tenTenFrancSum = new Sum(Money.franc(10), Money.franc(10));
        assertEquals(tenTenFrancSum , createFiveFiveFrancSum.times(2));
    }

    // Multiplying a Sum by 0 returns the amount as 0 and not an error
    @Test
    void multiplySumByZero() {
        Sum zeroZeroFrancSum = new Sum(Money.franc(0), Money.franc(0));
        assertEquals(zeroZeroFrancSum, createFiveFiveFrancSum.times(0));
    }

    // Multiplying a Sum by a negative number gives an error message
    @Test
    void multiplySumByANegativeNumber() {
        ArithmeticException thrown = Assertions.assertThrows(ArithmeticException.class, () -> {
            createFiveFiveFrancSum.times(-2);
        });
        assertEquals("Cannot multiply a Sum by a negative number", thrown.getMessage());


    }

    // Return false if the argument is Null
    @Test
    public void doesEqualsMethodCoverNonNullity() {
        assertNotEquals(null, createFiveFiveFrancSum);
    }

    // That two identical sums are equal
    @Test
    public void twoSameSumsAreEqual() {
        assertEquals(createFiveFiveFrancSum, createFiveFiveFrancSum);
        assertEquals(createFiveFourFrancSum, createFiveFourFrancSum);
    }

    // That two sums with augend and addends swapped are equal
    @Test
    public void areAugendAndAddendInterchangeable() {
        Sum fourFiveFrancSum = new Sum(createFourFranc(), createFiveFranc());
        assertEquals(createFiveFourFrancSum, fourFiveFrancSum);
    }

    // That two sums with different components are not equal
    @Test
    public void twoDifferentSumsAreNotEqual() {
        assertNotEquals(createFiveFiveFrancSum, createFiveFourFrancSum);
    }

    // That a Sum object is not seen as equal to a String
    @Test
    public void twoDifferentTypesAreNotEqual() {
        assertNotEquals(createFiveFiveFrancSum,"deloitte");
    }

    // That the toString() method outputs the amount and currency of its components
    @Test
    void testOutputtingASumAsItsComponents() {
        assertEquals("5 CHF 5 CHF", createFiveFiveFrancSum.toString());
        assertEquals("5 CHF 4 CHF", createFiveFourFrancSum.toString());
    }





}