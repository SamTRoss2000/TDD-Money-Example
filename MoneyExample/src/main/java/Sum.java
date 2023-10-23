class Sum implements Expression {
    Expression augend;
    Expression addend;

    Sum(Expression augend, Expression addend) {
        if (augend == null) throw new IllegalArgumentException("Augend cannot be null");
        if (addend == null) throw new IllegalArgumentException("Addend cannot be null");
        this.augend = augend;
        this.addend = addend;
    }

    public Money reduce(Bank bank, String to) {
        int amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount;
        return new Money(amount, to);
    }

    public Expression plus(Expression addend) {
        return new Sum(this, addend);
    }

    public Expression times(int multiplier) {
        if (multiplier < 0) throw new ArithmeticException("Cannot multiply a Sum by a negative number");
        return new Sum(augend.times(multiplier), addend.times(multiplier));
    }


    public String currency() {
        return augend.currency() + " " + addend.currency();
    }

    public boolean equals(Object object) {
        if (object == null) return false;
        if (object.getClass() != this.getClass()) return false;
        Sum other = (Sum) object;
        return other.augend.equals(this.augend)
                && other.addend.equals(this.addend)
                || other.addend.equals(this.augend)
                && other.augend.equals(this.addend);

    }

    // change this later so toString returns as normal
    public String toString() {
        return augend + " " + addend;
    }


}
