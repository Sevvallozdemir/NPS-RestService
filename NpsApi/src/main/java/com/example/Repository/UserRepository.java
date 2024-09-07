package com.example.Repository;

import com.example.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByPhoneNumberAndPassword(String phoneNumber, String password);
    boolean existsByUserId(String userId);
    void deleteByUserId(String userId);
    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findAll();

}
