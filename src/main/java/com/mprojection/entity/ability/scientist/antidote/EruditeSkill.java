package com.mprojection.entity.ability.scientist.antidote;

import com.mprojection.entity.ability.Ability;

import java.util.ArrayList;
import java.util.List;

public class EruditeSkill extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();

    static {
        NEXT.add(new GeniusSkill());
    }

    public EruditeSkill() {
        super("ability.scientist.antidote.erudite.name", "ability.scientist.antidote.erudite.description", 120, false);
    }

    @Override
    public List<Ability> getNext() {
        return NEXT;
    }


}
