package com.mprojection.controller;

import com.mprojection.entity.Translation;
import com.mprojection.util.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HomeController {

    @Autowired
    private Translator translator;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Translation index() {
        String s = translator.translate("ability.zombie.attack.power.name");
        return new Translation(s, s);
    }
}
