package com.mprojection.util;

import com.mprojection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {

    @Autowired
    private UserService userService;

    public Long findNearestUserOfDifferentGender(Long executor) {
        return userService.findNearestUserOfDifferentGender(executor).getId();
    }

}
