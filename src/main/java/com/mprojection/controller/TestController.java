package com.mprojection.controller;

import com.mprojection.entity.Point;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class TestController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Point index() {
        Point entity = new Point();
        //entity.setName(DatabaseConfig.getConnectionUrl());
        return entity;
    }

}
