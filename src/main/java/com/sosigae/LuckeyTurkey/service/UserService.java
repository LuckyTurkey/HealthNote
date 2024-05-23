package com.sosigae.LuckeyTurkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosigae.LuckeyTurkey.dao.UserDao;
import com.sosigae.LuckeyTurkey.domain.User;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public void registerMember(User user) {
        userDao.registerMember(user);
    }

    public User loginMember(String user_id, String password) {
        int userCount = userDao.isValidUser(user_id, password);
        if (userCount > 0) {
            return userDao.loginMember(user_id, password);
        } else {
            throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
        }
    }

    public void deleteMember(User user) {
        userDao.deleteMember(user);
    }

    public void updateMember(User user) {
        userDao.updateMember(user);
    }
}