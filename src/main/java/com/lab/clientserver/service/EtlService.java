package com.lab.clientserver.service;

import com.lab.clientserver.etl.RawDataObject;
import com.lab.clientserver.model.User;
import com.lab.clientserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EtlService {

     @Autowired
     private UserRepository userRepository; //  якщо є UserRepository для збереження в БД

    /**
     * Етап видобування (Extract)
     * Симулює отримання даних з одного або декількох джерел.
     */
    public List<RawDataObject> extractData() {
        System.out.println("ETL [Extract]: Початок видобування даних...");
        List<RawDataObject> rawDataList = new ArrayList<>();

        // Приклад "сирих" даних
        rawDataList.add(new RawDataObject( "Роман", "Процак", 30, "roma.protsak@example.com"));
        rawDataList.add(new RawDataObject("Марія", "Сидоренко", 25, "maria.odf123s@test.com"));
        rawDataList.add(new RawDataObject("Петро", "Іванов", 42, "petro-ivanov")); // Некоректний email
        rawDataList.add(new RawDataObject("Ольга", "Коваленко", 28, "olsdf@example.net"));

        System.out.println("ETL [Extract]: Видобуто " + rawDataList.size() + " записів.");
        return rawDataList;
    }

    /**
     * Етап трансформації (Transform)
     * Перетворює "сирі" дані у формат сутності User.
     * Включає очищення, валідацію, об'єднання полів тощо.
     */
    public List<User> transformData(List<RawDataObject> rawDataList) {
        System.out.println("ETL [Transform]: Початок трансформації даних...");
        List<User> transformedUsers = rawDataList.stream()
            .map(rawData -> {
                User user = new User();

                // Трансформація імені: об'єднання firstName та lastName
                user.setName(rawData.getFirstName() + " " + rawData.getLastName());

                // Трансформація віку
                user.setAge(rawData.getUserAge());

                // Трансформація та валідація email
                String email = rawData.getContactEmail();
                if (email != null && email.contains("@") && (email.endsWith(".com") || email.endsWith(".net"))) {
                    user.setEmail(email.toLowerCase()); // Стандартизація: переведення в нижній регістр
                } else {
                    System.out.println("ETL [Transform]: Некоректний email '" + email + "' для користувача " + user.getName() + ". Встановлено null.");
                    user.setEmail(null); // Обробка невалідних даних
                }
                System.out.println("ETL [Transform]: Трансформовано: " + rawData.getFirstName() + " -> " + user.getName());
                return user;
            })
            .collect(Collectors.toList());

        System.out.println("ETL [Transform]: Трансформовано " + transformedUsers.size() + " користувачів.");
        return transformedUsers;
    }

    /**
     * Етап завантаження (Load)
     * Завантажує трансформовані дані у цільове сховище (наприклад, БД).
     * У цьому прикладі ми симулюємо завантаження, виводячи дані в консоль.
     */
    public void loadData(List<User> users) {
        System.out.println("ETL [Load]: Початок завантаження даних...");
        if (users.isEmpty()) {
            System.out.println("ETL [Load]: Немає даних для завантаження.");
            return;
        }


         userRepository.saveAll(users); // Потребує UserRepository та налаштованої БД

        System.out.println("ETL [Load]: Симуляція завантаження " + users.size() + " користувачів:");
        users.forEach(user -> {
            System.out.println("ETL [Load]: Завантаження користувача - " + user.toString());
            userRepository.save(user);
        });
        System.out.println("ETL [Load]: Завантаження завершено.");
    }

    /**
     * Запускає повний ETL-процес.
     */
    public String runFullEtlProcess() {
        System.out.println("ETL: Запуск повного процесу ETL...");
        List<RawDataObject> extractedData = extractData();
        List<User> transformedData = transformData(extractedData);
        loadData(transformedData);
        String summary = "ETL процес завершено. Видобуто: " + extractedData.size() +
                         ", Трансформовано: " + transformedData.size() +
                         ", Завантажено (симуляція): " + transformedData.size() + ".";
        System.out.println(summary + " Перевірте логи консолі для деталей.");

        return summary;
    }
}