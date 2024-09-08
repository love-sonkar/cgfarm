package com.cgfarm.controller;


import com.cgfarm.entity.Users;
import com.cgfarm.links.UsersLinks;
import com.cgfarm.origin.Origin;
import com.cgfarm.service.ErrorReportService;
import com.cgfarm.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = UsersLinks.CREATE)
    public Users createUser(@RequestBody Users users) {
        try {
            return usersService.createUsers(users);
        }catch (Exception e ){
            errorReportService.generateErrorReport(e, "controller", "UsersController", "createUsers");
            return null;
        }
    }
}
