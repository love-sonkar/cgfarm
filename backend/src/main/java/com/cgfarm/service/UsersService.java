package com.cgfarm.service;
import com.cgfarm.entity.Users;
import com.cgfarm.entity.dto.SimpleUser;
import com.cgfarm.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Slf4j
@Component
@Transactional
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    ModelMapper modelMapper = new ModelMapper();

    public Users getUserByName(String name) {
        return usersRepository.findByName(name);
    }

    public Users getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public Users getUserByContact(String contact) {
        return usersRepository.findByContact(contact);
    }

    public ResponseEntity<?> adminLogin(String name, String password) {
        Users user = getUserByName(name);
        if(user!=null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean passwordIsValid = bCryptPasswordEncoder.matches(password, user.getPassword());
            if(passwordIsValid) {
                SimpleUser simpleUser = modelMapper.map(user, SimpleUser.class);
                return ResponseEntity.ok(simpleUser);
            }
        }
        return ResponseEntity.status(400).body("Admin not found");
    }

    public ResponseEntity<?> loginUser(String contact, String password) {
        Users user = getUserByContact(contact);
        if(user!=null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean passwordIsValid = bCryptPasswordEncoder.matches(password, user.getPassword());
            if(passwordIsValid) {
                SimpleUser simpleUser = modelMapper.map(user, SimpleUser.class);
                return ResponseEntity.ok(simpleUser);
            }
        }
        return ResponseEntity.status(400).body("User not found");
    }

    public ResponseEntity<?> createUsers(Users users) {
        if(users.getEmail() == null) {
            return ResponseEntity.status(400).body("Email Not Found");
        }
        if(users.getName() == null) {
            return ResponseEntity.status(400).body("Name Not Found");
        }
        if(users.getPassword() == null) {
            return ResponseEntity.status(400).body("Password Not Found");
        }
        if(users.getContact() == null) {
            return ResponseEntity.status(400).body("Contact Not Found");
        }
        Users users1 =  getUserByEmail(users.getEmail());
        if(users1 != null) {
            return ResponseEntity.status(400).body("Email exist");
        }
        users1 = getUserByContact(users.getContact());
        if(users1 != null) {
            return ResponseEntity.status(400).body("contact exist");
        }
        String password = users.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptedPassword = bCryptPasswordEncoder.encode(password);
        users.setPassword(bCryptedPassword);
        users = createUser(users);
        if(users !=null) {
            SimpleUser simpleUser = modelMapper.map(users, SimpleUser.class);
            return ResponseEntity.ok(simpleUser);
        }
        return ResponseEntity.status(400).body("Can't save users");
    }

    public ResponseEntity<?> createAdmin(Users users) {
        if(users.getEmail() == null) {
            return ResponseEntity.status(400).body("Email Not Found");
        }
        if(users.getName() == null) {
            return ResponseEntity.status(400).body("Name Not Found");
        }
        if(users.getPassword() == null) {
            return ResponseEntity.status(400).body("Password Not Found");
        }
        if(users.getContact() == null) {
            return ResponseEntity.status(400).body("Contact Not Found");
        }
        Users users1 =  getUserByEmail(users.getEmail());
        if(users1 != null) {
            return ResponseEntity.status(400).body("Email exist");
        }
        users1 = getUserByContact(users.getContact());
        if(users1 != null) {
            return ResponseEntity.status(400).body("contact exist");
        }
        String password = users.getPassword();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptedPassword = bCryptPasswordEncoder.encode(password);
        users.setPassword(bCryptedPassword);
        users.setPermission("admin");
        users = createUser(users);
        if(users !=null) {
            SimpleUser simpleUser = modelMapper.map(users, SimpleUser.class);
            return ResponseEntity.ok(simpleUser);
        }
        return ResponseEntity.status(400).body("Can't save Admin");
    }

    public Users createUser(Users users) {
        long currentTimeStamp = new Timestamp(System.currentTimeMillis()).getTime();
        users.setCreateTimeStamp(currentTimeStamp);
        users.setModifiedTimeStamp(currentTimeStamp);
        return save(users);
    }

    public Users save(Users users) {
        try {
            return usersRepository.save(users);
        }catch (Exception e) {
            log.error("error saving in users", e);
            return null;
        }
    }
}
