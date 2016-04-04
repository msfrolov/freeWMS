package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Preconditions.checkNotNull;

public class DocumentExpense extends Document {
    private Warehouse sender;
    private Counterpart recipient;

    @Override
    public String toString() {
        return "DocumentExpense{" +
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
