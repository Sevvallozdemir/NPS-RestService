package com.example.Controller;

import com.example.Model.Answer;
import com.example.Model.Response;
import com.example.Model.Survey;
import com.example.Repository.AnswerRepository;
import com.example.Repository.ResponseRepository;
import com.example.Repository.SurveyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.Model.User;
import com.example.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private ResponseRepository responseRepository;
    @Autowired
    AnswerRepository answerRepository;

    public Optional<User> getUserById(String userId) {
        return userRepository.findById(userId);
    }

    public boolean addUser(User user){
        log.info("Adding user:{}",user);
        // Phone number validation
        if (user.getPhoneNumber().length() != 10) {
            log.error("Phone number must be 10 digits long: {}", user.getPhoneNumber());
            return false;
        }

        try {
            user.setLastUpdateDate(LocalDateTime.now());
            userRepository.insert(user);
            log.info("User added successfully.");
            return true;
        } catch (Exception e) {
            log.error("Error occurred while adding user: {}", user, e);
            return false;
        }
    }
    public User getUser(String phoneNumber, String password){
        log.info("Fetching user with phonenumber:{} and password:{}",phoneNumber,password);
        return userRepository.findByPhoneNumberAndPassword(phoneNumber, password)
                .orElseGet(() -> {
                    log.warn("No user found with phone number: {} and password: {}", phoneNumber, password); // Kullanıcı bulunamadı
                    return null;
                });
    }

    public boolean updateUser(User user) {
        log.info("Updating user:{}",user);
        if (userRepository.existsByUserId(user.getUserId())) {
            try {
                user.setLastUpdateDate(LocalDateTime.now());
                userRepository.save(user);
                log.info("User updated successfully.");
                return true;
            } catch (Exception e) {
                log.error("Error occurred while updating user: {}", user, e);
                return false;
            }

        }
        return false;
    }

    public boolean deleteUser(User user) {
        log.info("Deleting user:{}",user);
        if (userRepository.existsByUserId(user.getUserId())) {
            try {
                userRepository.deleteByUserId(user.getUserId());
                log.info("User deleted successfully.");
                return true;
            }catch (Exception e) {
                log.error("Error occurred while deleting user: {}", user, e);
                return false;
            }
        }
        return false;
    }

    public Optional<User> login(String phoneNumber, String password) {
        log.info("Logging user with phonenumber:{} and password:{}", phoneNumber, password);
        Optional<User> user = userRepository.findByPhoneNumberAndPassword(phoneNumber, password);

        if(user.isPresent()){
            if(isUserInactive(phoneNumber)){
                throw new IllegalStateException("Kullanıcı pasif. Giriş yapılamaz.");
            }

            User foundUser = user.get();
            foundUser.setLastUpdateDate(LocalDateTime.now());
            userRepository.save(foundUser);
        }
        return user;
    }

    public boolean isUserInactive(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        if (user.isPresent()) {
            return !user.get().isStatus();
        }
        throw new IllegalArgumentException("Kullanıcı bulunamadı.");

    }




    public User register(User user) {
        log.info("Registering user:{}",user);
        if (userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new IllegalArgumentException("Bu telefon numarası ile zaten bir kullanıcı var.");
        }
        user.setLastUpdateDate(LocalDateTime.now());
        return userRepository.save(user);
    }




    /*@Scheduled(fixedRate = 60000) // 1 dakikada bir çalışacak şekilde ayarlanmıştır
    public void resetScoreIfInactive() {
        log.info("Users' score values are checked...");
        userRepository.findAll().forEach(user -> {
            LocalDateTime lastUpdateDate = user.getLastUpdateDate();
            if (lastUpdateDate != null) {
                Duration duration = Duration.between(lastUpdateDate, LocalDateTime.now());
                if (duration.toMinutes() > 2) {
                    // 2 dakikadan fazla süre geçtiyse score'u sıfırlıyoruz
                    user.getAnswers().forEach(answer -> {
                        answer.setScore(0);
                        answerRepository.save(answer);
                    });
                    user.setLastUpdateDate(LocalDateTime.now()); // Tarihi tekrar güncelliyoruz
                    userRepository.save(user);
                    log.info("Score reset for user {}", user.getUserId());
                }
            }
        });
    }*/


}


