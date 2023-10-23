public class MoneyFactory {

    /**
     *
     * @return  a new instance of Money
     */
    public static Money threeDollar() {
        return Money.dollar(3);
    }

    /**
     *
     * @return  a new instance of Money (dollar)
     */
    public static Expression dollar(int amount) {
        return Money.dollar(amount);
    }

    /**
     *
     * @return  a new instance of Money (franc)
     */
    public static Expression franc(int amount) {
        return Money.franc(amount);
    }

    /**
     *
     * @return  a new instance of a Sum composed of a identical pair of Money
     */
    public static Sum doubleExpression(Expression money) {
        return new Sum(money, money);
    }

    /**
     *
     * @return  a new instance of a Sum composed of two different Moneys
     */
    public static Sum sumExpression(Expression money1, Expression money2) {
        return new Sum(money1, money2);
    }

//    public static void francToDollarRate() {
//        Bank bank = new Bank();
//        bank.addRate("CHF", "USD", 2);
//    }

}
