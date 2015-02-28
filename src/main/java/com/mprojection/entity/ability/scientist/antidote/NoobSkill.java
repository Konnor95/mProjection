package com.mprojection.entity.ability.scientist.antidote;

import com.mprojection.entity.ability.Ability;

import java.util.ArrayList;
import java.util.List;

public class NoobSkill extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();

    static {
        NEXT.add(new EruditeSkill());
    }

    public NoobSkill() {
        super("ability.scientist.antidote.noob.name", "ability.scientist.antidote.noob.description", 0, true);
    }

    @Override
    public List<Ability> getNext() {
        return NEXT;
    }
}
