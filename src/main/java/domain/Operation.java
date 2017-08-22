package domain;

import java.time.LocalDate;
import java.util.Date;

public class Operation {

    private LocalDate date;
    private double amount;
    private String type;


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
