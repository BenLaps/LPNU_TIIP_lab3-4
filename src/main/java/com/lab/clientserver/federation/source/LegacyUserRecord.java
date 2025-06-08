package com.lab.clientserver.federation.source;

// Уявний запис зі старої системи
public class LegacyUserRecord {
    private String userId;
    private String fullUserName;
    private int ageYears;
    private String contactInfo;

    public LegacyUserRecord(String userId, String fullUserName, int ageYears, String contactInfo) {
        this.userId = userId;
        this.fullUserName = fullUserName;
        this.ageYears = ageYears;
        this.contactInfo = contactInfo;
    }

    // Getters
    public String getUserId() { return userId; }
    public String getFullUserName() { return fullUserName; }
    public int getAgeYears() { return ageYears; }
    public String getContactInfo() { return contactInfo; }
}