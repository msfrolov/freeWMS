package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Preconditions.checkNotNull;

public class DocumentReceipt extends Document {
    private Counterpart sender;
    private Warehouse recipient;

    @Override
    public String toString() {
        return "DocumentReceipt{" +
                super.toString() +
                "sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }

    public Counterpart getSender() {
        return sender;
    }

    public void setSender(Counterpart sender) {
        checkNotNull(sender);
        this.sender = sender;
    }

    public Warehouse getRecipient() {
        return recipient;
    }

    public void setRecipient(Warehouse recipient) {
        checkNotNull(recipient);
        this.recipient = recipient;
    }
}
