package ue_2.s2410238049;

import lecture.chapter03.IKey;

public class TelephoneBookEntryKey implements IKey {

    protected String firstName;
    protected String surName;

    public TelephoneBookEntryKey(String firstName, String surName) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public boolean matches(Object data) {
        if (!(data instanceof TelephoneBookEntry)) {
            return false;
        }
        TelephoneBookEntry entry = (TelephoneBookEntry) data;
        if (firstName == null || surName == null) {
            return false;
        }
        return firstName.equals(entry.getFirstName())
                && surName.equals(entry.getSurName());
    }
}

