package com.lab.clientserver.federation.source;

// Уявні дані з нового сервісу профілів
public class ProfileData {
    private Long profileId;
    private String givenName;
    private String familyName;
    private int currentAge;
    private String primaryEmail;

    public ProfileData(Long profileId, String givenName, String familyName, int currentAge, String primaryEmail) {
        this.profileId = profileId;
        this.givenName = givenName;
        this.familyName = familyName;
        this.currentAge = currentAge;
        this.primaryEmail = primaryEmail;
    }

    // Getters
    public Long getProfileId() { return profileId; }
    public String getGivenName() { return givenName; }
    public String getFamilyName() { return familyName; }
    public int getCurrentAge() { return currentAge; }
    public String getPrimaryEmail() { return primaryEmail; }
}