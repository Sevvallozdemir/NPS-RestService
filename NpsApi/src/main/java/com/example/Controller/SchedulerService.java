package com.example.Controller;


import com.example.Model.User;
import com.example.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class SchedulerService {
    @Autowired
    private UserRepository userRepository;

    // İlk güncelleme için kullanılacak gecikme süresi
    //private static final long INITIAL_DELAY = 120000; // 2 dakika = 120000 ms

    // Her 2 dakikada bir çalışacak
    @Scheduled(fixedRate = 700000) // 60.000 ms = 1dakika
    public void updateUserPoints() {
        log.info("Scheduler is running to update user points.");

        List<User> users = userRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (User user : users) {
            if (user.getLastUpdateDate() != null && now.minusMinutes(2).isAfter(user.getLastUpdateDate())) {
                user.setUserPoint(0);
                userRepository.save(user);
                log.info("User with ID {} had their userPoint reset to 0.", user.getUserId());
            }
        }
    }
}
