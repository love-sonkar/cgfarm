package com.cgfarm.service;
import com.cgfarm.entity.Users;
import com.cgfarm.repository.UsersRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    Gson gson = new Gson();

    public Users createUsers(Users users) {
        return users;
    }
}
