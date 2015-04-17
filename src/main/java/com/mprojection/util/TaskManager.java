package com.mprojection.util;

import com.mprojection.entity.PublicUserInfo;
import com.mprojection.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskManager {

    @Autowired
    private UserService userService;

    public Long findNearestUser(Long executor) {
        PublicUserInfo user = userService.findNearestUser(executor);
        return user == null ? null : user.getId();
    }

    public Long findNearestUserOfDifferentGender(Long executor) {
        PublicUserInfo user = userService.findNearestUserOfDifferentGender(executor);
        return user == null ? null : user.getId();
    }

}
