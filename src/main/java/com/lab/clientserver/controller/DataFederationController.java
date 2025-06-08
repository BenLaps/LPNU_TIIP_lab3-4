package com.lab.clientserver.controller;

import com.lab.clientserver.federation.dto.FederatedUserInfo;
import com.lab.clientserver.federation.service.DataFederationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/federated-api")
public class DataFederationController {

    private final DataFederationService dataFederationService;

    @Autowired
    public DataFederationController(DataFederationService dataFederationService) {
        this.dataFederationService = dataFederationService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<FederatedUserInfo>> getUsers(
            @RequestParam(required = false) String name) {
        System.out.println("API: Отримано запит /federated-api/users" + (name != null ? "?name=" + name : ""));
        List<FederatedUserInfo> users = dataFederationService.getFederatedUsers(name);
        if (users.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }
}