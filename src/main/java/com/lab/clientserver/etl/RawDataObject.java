package com.lab.clientserver.etl; // Зазвичай має бути пакет DTO

public class RawDataObject {

    private Long id;
    private String firstName;
    private String lastName;
    private int userAge;
    private String contactEmail;

    // Конструктор
    public RawDataObject( String firstName, String lastName, int userAge, String contactEmail) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userAge = userAge;
        this.contactEmail = contactEmail;
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getId() {return id;}

    public int getUserAge() {
        return userAge;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    // Setters
    public void setId(Long id) {this.id = id;}

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}