package com.mprojection.entity.ability.zombie.attack;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;

import java.util.ArrayList;
import java.util.List;

public class SimpleAttack extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();
    private static final float ATTACK_FACTOR = 1f;

    static {
        NEXT.add(new PowerAttack());
    }

    public SimpleAttack() {
        super("ability.zombie.attack.simple.name", "ability.zombie.attack.simple.description", 0, true);
    }

    @Override
    public List<Ability> getNext() {
        return NEXT;
    }


    @Override
    public void apply(FullUserInfo user, Translator translator) {
        super.apply(user, translator);
        user.setAbilityAttackFactor(ATTACK_FACTOR);
    }
}
