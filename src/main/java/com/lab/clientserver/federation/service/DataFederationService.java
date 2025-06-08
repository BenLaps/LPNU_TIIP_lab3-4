package com.lab.clientserver.federation.service;

import com.lab.clientserver.federation.dto.FederatedUserInfo;
import com.lab.clientserver.federation.provider.LegacyUserSystemProvider;
import com.lab.clientserver.federation.provider.NewProfileServiceProvider;
import com.lab.clientserver.federation.source.LegacyUserRecord;
import com.lab.clientserver.federation.source.ProfileData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataFederationService {

    private final LegacyUserSystemProvider legacyProvider;
    private final NewProfileServiceProvider profileProvider;

    @Autowired
    public DataFederationService(LegacyUserSystemProvider legacyProvider, NewProfileServiceProvider profileProvider) {
        this.legacyProvider = legacyProvider;
        this.profileProvider = profileProvider;
    }

    public List<FederatedUserInfo> getFederatedUsers(String nameQuery) {
        System.out.println("FederationService: Отримання федеративних даних за запитом '" + nameQuery + "'");

        // 1. Запит до першого джерела (Legacy System) або старасистема
        List<LegacyUserRecord> legacyResults = legacyProvider.findUsers(nameQuery);
        List<FederatedUserInfo> federatedFromLegacy = legacyResults.stream()
                .map(legacyUser -> new FederatedUserInfo(
                        "legacy:" + legacyUser.getUserId(),
                        legacyUser.getFullUserName(),
                        legacyUser.getAgeYears(),
                        extractEmail(legacyUser.getContactInfo()),
                        "Legacy User System"
                ))
                .collect(Collectors.toList());
        System.out.println("FederationService: Отримано " + federatedFromLegacy.size() + " записів з Legacy System.");

        // 2. Запит до другого джерела (New Profile Service) або нова система
        List<ProfileData> profileResults = profileProvider.searchProfiles(nameQuery);
        List<FederatedUserInfo> federatedFromProfile = profileResults.stream()
                .map(profile -> new FederatedUserInfo(
                        "profile:" + profile.getProfileId(),
                        profile.getGivenName() + " " + profile.getFamilyName(),
                        profile.getCurrentAge(),
                        profile.getPrimaryEmail(),
                        "New Profile Service"
                ))
                .collect(Collectors.toList());
        System.out.println("FederationService: Отримано " + federatedFromProfile.size() + " записів з New Profile Service.");


        // 3. Об'єднання результатів
        List<FederatedUserInfo> combinedResults = Stream.concat(federatedFromLegacy.stream(), federatedFromProfile.stream())
                .collect(Collectors.toList());
        
        System.out.println("FederationService: Всього знайдено " + combinedResults.size() + " федеративних записів.");
        return combinedResults;
    }


    private String extractEmail(String contactInfo) {
        if (contactInfo != null && contactInfo.contains("@")) {
            return contactInfo;
        }
        return "N/A"; // Якщо не email
    }
}