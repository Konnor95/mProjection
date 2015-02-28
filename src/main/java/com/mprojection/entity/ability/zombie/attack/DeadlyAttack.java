package com.mprojection.entity.ability.zombie.attack;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;

import java.util.List;

public class DeadlyAttack extends Ability {

    private static final double ATTACK_FACTOR = 3;

    public DeadlyAttack() {
        super("ability.zombie.attack.deadly.name", "ability.zombie.attack.deadly.description", 300, false);
    }

    @Override
    public List<Ability> getNext() {
        return null;
    }

    @Override
    public void apply(FullUserInfo user, Translator translator) {
        super.apply(user, translator);
        user.setAttackFactor(ATTACK_FACTOR);
    }
}
