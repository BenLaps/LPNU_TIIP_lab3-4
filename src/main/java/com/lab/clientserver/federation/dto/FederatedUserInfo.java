package com.lab.clientserver.federation.dto;

public class FederatedUserInfo {
    private String federatedId; // Унікальний ID у федеративній системі, напр. "legacy:123", "profile:456"
    private String name;
    private int age;
    private String email;
    private String sourceSystem;

    public FederatedUserInfo(String federatedId, String name, int age, String email, String sourceSystem) {
        this.federatedId = federatedId;
        this.name = name;
        this.age = age;
        this.email = email;
        this.sourceSystem = sourceSystem;
    }

    // Getters
    public String getFederatedId() { return federatedId; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getSourceSystem() { return sourceSystem; }

    @Override
    public String toString() {
        return "FederatedUserInfo{" +
               "federatedId='" + federatedId + '\'' +
               ", name='" + name + '\'' +
               ", age=" + age +
               ", email='" + email + '\'' +
               ", sourceSystem='" + sourceSystem + '\'' +
               '}';
    }
}