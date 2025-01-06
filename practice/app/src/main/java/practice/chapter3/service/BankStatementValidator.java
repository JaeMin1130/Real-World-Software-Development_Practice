package practice.chapter3.service;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import practice.chapter3.domain.Notification;

public class BankStatementValidator {
    private String description;
    private String date;
    private String amount;

    public BankStatementValidator(final String description, final String date, final String amount) {
        this.description = Objects.requireNonNull(description);
        this.date = Objects.requireNonNull(date);
        this.amount = Objects.requireNonNull(amount);
    }

    public Notification validate() {
        final Notification notification = new Notification();
        if (this.description.length() > 100) {
            notification.addError("The description is too long.");
        }

        final LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(this.date);
            if (parsedDate.isAfter(LocalDate.now())) {
                notification.addError("Date cannot be in the future");
            }
        } catch (DateTimeParseException e) {
            notification.addError("Invalid format for date");
        }

        final double parsedAmount;
        try {
            parsedAmount = Double.parseDouble(this.amount);
        } catch (NumberFormatException e) {
            notification.addError("Invalid format for amount");
        }

        return notification;
    }
}
