package domain;

import java.util.Date;

public class Operation {

    private Date date;
    private double amount;


    public static final class OperationBuilder {
        private Date date;
        private double amount;

        private OperationBuilder() {
        }

        public static OperationBuilder anOperation() {
            return new OperationBuilder();
        }

        public OperationBuilder withDate(Date date) {
            this.date = date;
            return this;
        }

        public OperationBuilder withAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Operation build() {
            Operation operation = new Operation();
            operation.amount = this.amount;
            operation.date = this.date;
            return operation;
        }
    }
}
