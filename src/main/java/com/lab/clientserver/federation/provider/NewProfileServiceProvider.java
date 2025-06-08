package com.lab.clientserver.federation.provider;

import com.lab.clientserver.federation.source.ProfileData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewProfileServiceProvider {
    private final List<ProfileData> profiles = new ArrayList<>();

    public NewProfileServiceProvider() {
        // Імітація даних
        profiles.add(new ProfileData(101L, "Protsak", "Roman", 28, "roma.w@newservice.com"));
        profiles.add(new ProfileData(102L, "Ola", "Petrovich", 35, "dsfdsf.b@example.com"));
        profiles.add(new ProfileData(103L, "John", "sdf.", 25, "john@newservice.com"));
    }

    public List<ProfileData> searchProfiles(String nameQuery) {
        System.out.println("ProfileProvider: Пошук за '" + nameQuery + "'");
        if (nameQuery == null || nameQuery.isEmpty()) {
            return new ArrayList<>(profiles);
        }
        return profiles.stream()
                .filter(profile -> (profile.getGivenName() + " " + profile.getFamilyName()).toLowerCase().contains(nameQuery.toLowerCase()))
                .collect(Collectors.toList());
    }
}