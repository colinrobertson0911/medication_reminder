package com.github.robbo1811.medication_reminder.services;

import com.github.robbo1811.medication_reminder.model.User;
import com.github.robbo1811.medication_reminder.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public Optional<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
