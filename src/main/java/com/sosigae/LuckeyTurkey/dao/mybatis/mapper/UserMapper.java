package com.sosigae.LuckeyTurkey.dao.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sosigae.LuckeyTurkey.domain.User;

@Mapper
public interface UserMapper {
	User loginMember(@Param("id") String id, @Param("password") String password, @Param("is_admin") int is_admin);

    void registerMember(User user);

    void deleteMember(User user);

    void updateMember(User user);

    int isValidUser(@Param("id")String id, @Param("password") String password, @Param("is_admin") int is_admin);
    
    User findUserByCode(@Param("personal_code") String personal_code);

	User findByUserId(@Param("id")String id);
    
    User findByUser_Id(int userId);

}
