package com.mprojection.entity.ability.zombie.olfaction;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.UserType;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.util.measureunit.MeasureUnitConverter;

import java.util.ArrayList;
import java.util.List;

public class WeakOlfaction extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();
    private static final int RANGE = UserType.ZOMBIE.getDefaultVisibility();

    static {
        NEXT.add(new DevelopedOlfaction());
    }

    public WeakOlfaction() {
        super("ability.zombie.olfaction.weak.name", "ability.zombie.olfaction.weak.description", 0, true);
    }

    @Override
    public List<Ability> getNext() {
        return NEXT;
    }

    @Override
    public void apply(FullUserInfo user, Translator translator) {
        super.apply(user, translator);
        user.setVisibility(RANGE);
    }

    @Override
    protected String formatDescription(Translator translator, String description, MeasureUnit measureUnit, String locale) {
        MeasureUnitConverter converter = measureUnit.getConverter();
        int distance = converter.convertDistance(RANGE);
        return String.format(description, distance, measureUnit.getDistanceUnit(translator, locale));
    }
}
