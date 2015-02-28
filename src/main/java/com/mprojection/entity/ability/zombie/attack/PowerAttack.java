package com.mprojection.entity.ability.zombie.attack;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;

import java.util.ArrayList;
import java.util.List;

public class PowerAttack extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();
    private static final double ATTACK_FACTOR = 2;

    static {
        NEXT.add(new DeadlyAttack());
    }

    public PowerAttack() {
        super("ability.zombie.attack.power.name", "ability.zombie.attack.power.description", 120, false);
    }

    @Override
    public List<Ability> getNext() {
        return NEXT;
    }

    @Override
    public void apply(FullUserInfo user, Translator translator) {
        super.apply(user, translator);
        user.setDefenseFactor(ATTACK_FACTOR);
    }
}
