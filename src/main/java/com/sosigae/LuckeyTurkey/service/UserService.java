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

    public User loginMember(String id, String password) {
        int userCount = userMapper.isValidUser(id, password);
        if (userCount > 0) {
            return userMapper.loginMember(id, password);
        } else {
            throw new IllegalArgumentException("유효하지 않은 사용자입니다.");
        }
    }

    public int getUserIsAdmin(String id) {
		User user = userMapper.findByUserId(id);
		if (user != null) {
            return user.getIs_admin();
        } else {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
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