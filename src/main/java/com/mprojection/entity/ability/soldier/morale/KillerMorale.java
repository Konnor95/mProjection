package com.mprojection.entity.ability.soldier.morale;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;

import java.util.ArrayList;
import java.util.List;

public class KillerMorale extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();
    private static final float ATTACK_FACTOR = 2f;

    static {
        NEXT.add(new BerserkMorale());
    }

    public KillerMorale() {
        super("ability.soldier.morale.killer.name", "ability.soldier.morale.killer.description", 120, false);
    }

    @Override
    public List<Ability> getNext() {
        return NEXT;
    }

    @Override
    public void apply(FullUserInfo user, Translator translator) {
        super.apply(user, translator);
        user.setAbilityDefenseFactor(ATTACK_FACTOR);
    }
}