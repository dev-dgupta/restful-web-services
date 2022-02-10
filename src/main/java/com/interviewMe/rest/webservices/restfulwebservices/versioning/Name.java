package com.interviewMe.rest.webservices.restfulwebservices.versioning;

public class Name {

    private String firstName;
    private String middleName;
    private String thirdName;

    public Name() {
    }

    public Name(String firstName, String middleName, String thirdName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.thirdName = thirdName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", thirdName='" + thirdName + '\'' +
                '}';
    }
}
