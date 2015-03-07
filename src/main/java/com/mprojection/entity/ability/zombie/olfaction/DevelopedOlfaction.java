package com.mprojection.entity.ability.zombie.olfaction;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.util.measureunit.MeasureUnitConverter;

import java.util.ArrayList;
import java.util.List;

public class DevelopedOlfaction extends Ability {
    private static final List<Ability> NEXT = new ArrayList<>();
    private static final int RANGE = 150;

    static {
        NEXT.add(new ExtremeOlfaction());
    }

    public DevelopedOlfaction() {
        super("ability.zombie.olfaction.developed.name", "ability.zombie.olfaction.developed.name", 70, false);
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
