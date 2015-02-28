package com.mprojection.entity.ability.scientist.antidote;

import com.mprojection.entity.ability.Ability;

import java.util.List;

public class GeniusSkill extends Ability {

    public GeniusSkill() {
        super("ability.scientist.antidote.genius.name", "ability.scientist.antidote.genius.description", 300, false);
    }

    @Override
    public List<Ability> getNext() {
        return null;
    }
}
