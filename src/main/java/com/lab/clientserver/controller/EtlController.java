package com.lab.clientserver.controller;

import com.lab.clientserver.service.EtlService; // Переконайтесь, що імпорт правильний
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/etl") // Базовий шлях
public class EtlController {

    private final EtlService etlService;

    @Autowired // автоматично ін'єкція
    public EtlController(EtlService etlService) {
        this.etlService = etlService;
    }

    @GetMapping("/run")
    public ResponseEntity<String> triggerEtlProcess() {
        System.out.println("API: Отримано запит на запуск ETL-процесу.");
        String resultSummary = etlService.runFullEtlProcess();
        return ResponseEntity.ok("Вебсервіс запустив ETL-процес. Результат: " + resultSummary);
    }
}