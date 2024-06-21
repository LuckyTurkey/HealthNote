package com.sosigae.LuckeyTurkey.service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.DoctorMapper;
import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.HospitalMapper;
import com.sosigae.LuckeyTurkey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sosigae.LuckeyTurkey.dao.mybatis.mapper.UserMapper;
import com.sosigae.LuckeyTurkey.domain.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    public void registerMember(User user) {
        userMapper.registerMember(user);
    }

    public User loginMember(String id, String password, int is_admin) {
        int userCount = userMapper.isValidUser(id, password, is_admin);
        if (userCount > 0) {
            return userMapper.loginMember(id, password, is_admin);
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
    // 전화번호와 이름으로 user 찾기
    public User findUserByNameAndPhone(String name, String phone) {
        return userRepository.findByNameAndPhone(name, phone);
    }

    // user_id (pk)로 찾기
    public User findUserByUserId(int userId) {
        return userRepository.findByUserId(userId);
    }

    // id로 찾기
    public User findUserById(String id){
        return userMapper.findUserById(id);
    }

    public User findByUserId(String id){
        return userMapper.findByUserId(id);
    }
}