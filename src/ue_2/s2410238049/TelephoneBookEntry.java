package ue_2.s2410238049;

public class TelephoneBookEntry {

    protected String firstName;
    protected String surName;
    protected String street;
    protected String zipCode;
    protected String city;
    protected String[] phoneNumbers;

    public TelephoneBookEntry(String firstName,
                              String surName,
                              String street,
                              String zipCode,
                              String city,
                              String[] phoneNumbers) {
        this.firstName = firstName;
        this.surName = surName;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.phoneNumbers = phoneNumbers != null ? phoneNumbers.clone() : null;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String[] getPhoneNumbers() {
        return phoneNumbers != null ? phoneNumbers.clone() : null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(" ").append(surName).append("\n");
        sb.append(street).append("\n");
        sb.append(zipCode).append(" ").append(city).append("\n");
        sb.append("Phone: ");
        if (phoneNumbers != null) {
            for (int i = 0; i < phoneNumbers.length; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(phoneNumbers[i]);
            }
        }
        return sb.toString();
    }
}

