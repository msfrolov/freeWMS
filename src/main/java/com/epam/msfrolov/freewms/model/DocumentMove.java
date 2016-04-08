package com.epam.msfrolov.freewms.model;

import static com.epam.msfrolov.freewms.util.Common.checkNotNull;

public class DocumentMove extends Document {
    private Warehouse sender;
    private Warehouse recipient;

    @Override
    public String toString() {
        return "DocumentMove{" +
                super.toString() +
                "sender=" + sender +
                ", recipient=" + recipient +
                '}';
    }

    public Warehouse getRecipient() {
        return recipient;
    }

    public void setRecipient(Warehouse recipient) {
        checkNotNull(recipient);
        this.recipient = recipient;
    }

    public Warehouse getSender() {
        return sender;
    }

    public void setSender(Warehouse sender) {
        checkNotNull(sender);
        this.sender = sender;
    }
}
