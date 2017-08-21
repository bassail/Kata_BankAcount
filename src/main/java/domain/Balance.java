package domain;

public class Balance {

    double amount;

    public double depose(double amountOfDeposal) {
        return this.amount += amountOfDeposal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Balance balance = (Balance) o;

        return Double.compare(balance.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(amount);
        return (int) (temp ^ (temp >>> 32));
    }

    public static final class BalanceBuilder {
        double amount;

        private BalanceBuilder() {
        }

        public static BalanceBuilder aBalance() {
            return new BalanceBuilder();
        }

        public BalanceBuilder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Balance build() {
            Balance balance = new Balance();
            balance.amount = this.amount;
            return balance;
        }
    }
}
