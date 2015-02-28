package com.mprojection.entity.ability;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.UserType;
import com.mprojection.entity.ability.scientist.antidote.NoobSkill;
import com.mprojection.entity.ability.scientist.tracker.SimpleTracker;
import com.mprojection.entity.ability.soldier.morale.WarriorMorale;
import com.mprojection.entity.ability.soldier.radar.SimpleRadar;
import com.mprojection.entity.ability.zombie.attack.SimpleAttack;
import com.mprojection.entity.ability.zombie.olfaction.WeakOlfaction;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Abilities {

    @Autowired
    private Translator translator;
    private Map<UserType, List<Ability>> abilities;

    public Abilities() {
        abilities = new HashMap<>();
        abilities.put(UserType.SOLDIER, asList("1",
                new WarriorMorale(),
                new SimpleRadar()
        ));
        abilities.put(UserType.SCIENTIST, asList("2",
                new NoobSkill(),
                new SimpleTracker()
        ));
        abilities.put(UserType.ZOMBIE, asList("3",
                new SimpleAttack(),
                new WeakOlfaction()
        ));
    }


    public List<UserAbility> define(UserType userType, List<String> ids, MeasureUnit measureUnit) {
        List<UserAbility> userAbilities = new ArrayList<>();
        for (Ability ability : abilities.get(userType)) {
            userAbilities.add(new UserAbility(ability, ids, translator, measureUnit));
        }
        return userAbilities;
    }

    public Ability define(UserType userType, String id) {
        for (Ability ability : abilities.get(userType)) {
            Ability found = ability.get(id);
            if (found != null) {
                return found;
            }

        }
        return null;
    }

    public UserAbility define(FullUserInfo user, String id) {
        for (UserAbility ability : user.getAbilities()) {
            UserAbility found = ability.get(id);
            if (found != null) {
                return found;
            }

        }
        return null;
    }

    private List<Ability> asList(String prefix, Ability... items) {
        List<Ability> abilityList = new ArrayList<>();
        for (short i = 1; i <= items.length; i++) {
            Ability item = items[i - 1];
            item.setId(prefix, i);
            abilityList.add(item);
        }
        return abilityList;
    }

}
