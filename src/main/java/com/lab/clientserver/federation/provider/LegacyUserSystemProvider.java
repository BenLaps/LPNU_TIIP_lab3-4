package com.lab.clientserver.federation.provider;

import com.lab.clientserver.federation.source.LegacyUserRecord;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LegacyUserSystemProvider {
    private final List<LegacyUserRecord> legacyUsers = new ArrayList<>();

    public LegacyUserSystemProvider() {
        legacyUsers.add(new LegacyUserRecord("legacy-001", "PR Sr.", 55, "pr.sr@example.com"));
        legacyUsers.add(new LegacyUserRecord("legacy-002", "David", 42, "david.s@example.org"));
        legacyUsers.add(new LegacyUserRecord("legacy-003", "Petro ", 30, "petro@neverland.com"));

    }

    public List<LegacyUserRecord> findUsers(String nameQuery) {
        System.out.println("LegacyProvider: Пошук за '" + nameQuery + "'");
        if (nameQuery == null || nameQuery.isEmpty()) {
            return new ArrayList<>(legacyUsers);
        }
        return legacyUsers.stream()
                .filter(user -> user.getFullUserName().toLowerCase().contains(nameQuery.toLowerCase()))
                .collect(Collectors.toList());
    }
}
