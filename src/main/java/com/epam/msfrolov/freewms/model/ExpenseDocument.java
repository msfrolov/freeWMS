package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class ExpenseDocument extends Document {
    private Warehouse sender;
    private Counterpart recipient;

    @Override
    public String toString() {
        return "ExpenseDocument{" +
                super.toString() +
                "sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }

    public Warehouse getSender() {
        return sender;
    }

    public void setSender(Warehouse sender) {
        checkNotNull(sender);
        this.sender = sender;
    }

    public Counterpart getRecipient() {
        return recipient;
    }

    public void setRecipient(Counterpart recipient) {
        checkNotNull(recipient);
        this.recipient = recipient;
    }
}
