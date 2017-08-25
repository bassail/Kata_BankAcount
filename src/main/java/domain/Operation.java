package domain;

import java.io.PrintStream;
import java.time.LocalDate;

public class Operation {

    private LocalDate date;
    private double amount;
    private String type;
    private final String SEPARATOR = " | ";

    public void print(PrintStream printer) {
        printer.println(this.type + SEPARATOR + this.date + SEPARATOR + this.amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Operation operation = (Operation) o;

        if (Double.compare(operation.amount, amount) != 0) return false;
        if (date != null ? !date.equals(operation.date) : operation.date != null) return false;
        return type != null ? type.equals(operation.type) : operation.type == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public static final class OperationBuilder {
        private LocalDate date;
        private double amount;
        private String type;

        private OperationBuilder() {
        }

        public static OperationBuilder anOperation() {
            return new OperationBuilder();
        }

        public OperationBuilder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public OperationBuilder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public OperationBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public Operation build() {
            Operation operation = new Operation();
            operation.amount = this.amount;
            operation.date = this.date;
            operation.type = this.type;
            return operation;
        }
    }
}
