package com.example.Controller;

import com.example.Model.Answer;
import com.example.Model.Survey;
import com.example.Model.User;
import com.example.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/database")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @RequestMapping(value = "/addUser", method = {RequestMethod.POST})
    public boolean addUser(@RequestBody User user){
        return userService.addUser(user);


    }
    @RequestMapping(value = "/getUser", method = {RequestMethod.GET})
    public User getUser(@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                        @RequestParam(value = "password", required = false) String password){
        return userService.getUser(phoneNumber,password);
    }

    @RequestMapping(value = "/updateUser", method = {RequestMethod.PUT})
    public boolean updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @RequestMapping(value = "/deleteUser", method = {RequestMethod.DELETE})
    public boolean deleteUser(@RequestBody User user) {
        return userService.deleteUser(user);
    }

    @PostMapping("/login")   //GİRİŞ YAPMA METODU
    public ResponseEntity<?> login(@RequestParam String phoneNumber, @RequestParam String password) {
        try {
            Optional<User> user = userService.login(phoneNumber,password);
            if(user.isPresent()){
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Kullanıcı bulunamadı. Kayıt ekranına yönlendiriliyorsunuz");
            }
        }catch (IllegalStateException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }

    @PostMapping("/register")   //KAYIT OLMA METODU
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByPhoneNumber(user.getPhoneNumber()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bu telefon numarasi zaten mevcut.");  //kullanıcı mevcutsa hata
        }
        User savedUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser); // Kullanıcı başarıyla kaydedildi
    }
}

