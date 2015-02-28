package com.mprojection.entity.ability.soldier.morale;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;

import java.util.ArrayList;
import java.util.List;

public class WarriorMorale extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();
    private static final double ATTACK_FACTOR = 1;

    static {
        NEXT.add(new KillerMorale());
    }

    public WarriorMorale() {
        super("ability.soldier.morale.warrior.name", "ability.soldier.morale.warrior.description", 0, true);
    }

    @Override
    public List<Ability> getNext() {
        return NEXT;
    }


    @Override
    public void apply(FullUserInfo user, Translator translator) {
        super.apply(user, translator);
        user.setAttackFactor(ATTACK_FACTOR);
    }
}
