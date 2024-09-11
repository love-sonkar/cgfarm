package com.cgfarm.controller;


import com.cgfarm.entity.Users;
import com.cgfarm.links.UsersLinks;
import com.cgfarm.origin.Origin;
import com.cgfarm.service.ErrorReportService;
import com.cgfarm.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = Origin.CROSS_ORIGIN)
@RequestMapping(path = UsersLinks.USERS)
public class UsersController {

    @Autowired
    ErrorReportService errorReportService;

    @Autowired
    UsersService usersService;

    @PostMapping(path = UsersLinks.LOGIN)
    public ResponseEntity<?> loginUser(@RequestBody Users users) {
        try {
            return usersService.loginUser(users.getContact(), users.getPassword());
        }catch (Exception e ){
            errorReportService.generateErrorReport(e, "controller", "UsersController", "loginUser");
            return ResponseEntity.status(400).body("Something went wrong");
        }
    }

    @PostMapping(path = UsersLinks.ADMIN)
    public ResponseEntity<?> adminLogin(@RequestBody Users users) {
        try {
            return usersService.adminLogin(users.getName(), users.getPassword());
        }catch (Exception e ){
            errorReportService.generateErrorReport(e, "controller", "UsersController", "adminLogin");
            return ResponseEntity.status(400).body("Something went wrong");
        }
    }

    @PostMapping(path = UsersLinks.CREATE_ADMIN)
    public ResponseEntity<?> createAdmin(@RequestBody Users users) {
        try {
            return usersService.createAdmin(users);
        }catch (Exception e ){
            errorReportService.generateErrorReport(e, "controller", "UsersController", "createAdmin");
            return ResponseEntity.status(400).body("Something went wrong");
        }
    }

    @PostMapping(path = UsersLinks.CREATE)
    public ResponseEntity<?> createUser(@RequestBody Users users) {
        try {
            return usersService.createUsers(users);
        }catch (Exception e ){
            errorReportService.generateErrorReport(e, "controller", "UsersController", "createUsers");
            return ResponseEntity.status(400).body("Something went wrong");
        }
    }

}
