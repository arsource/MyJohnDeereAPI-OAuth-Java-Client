
package com.deere.api.axiom.generated.v3;

public class User extends Resource {

    private final static long serialVersionUID = 1L;
    protected String accountName;
    protected String givenName;
    protected String familyName;
    protected String userType;
    //protected Addresses addresses;
    //protected Organizations organizations;
    //protected PhoneNumbers phoneNumbers;
    //protected UserApplications userApplications;
    //protected DateTime lastLoggedInDate;
    protected String company;
    //protected ProfileState profileState;
    //protected EmailAddresses emailAddresses;


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
