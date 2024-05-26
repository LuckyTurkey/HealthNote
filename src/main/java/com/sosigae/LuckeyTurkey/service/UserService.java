package com.sosigae.LuckeyTurkey.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.UserMapper;
import com.sosigae.LuckeyTurkey.domain.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void registerMember(User user) {
    	userMapper.registerMember(user);
    }

    public User loginMember(String user_id, String password) {
        int userCount = userMapper.isValidUser(user_id, password);
        if (userCount > 0) {
            return userMapper.loginMember(user_id, password);
        } else {
            throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
        }
    }

    public void deleteMember(User user) {
    	userMapper.deleteMember(user);
    }

    public void updateMember(User user) {
    	userMapper.updateMember(user);
    }

    // 주민등록번호로 user 찾기
    public User findUserByCode(String personal_code) {
        return userMapper.findUserByCode(personal_code);
    }
}