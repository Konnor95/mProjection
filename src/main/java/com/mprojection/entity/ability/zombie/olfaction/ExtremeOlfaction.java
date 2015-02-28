package com.mprojection.entity.ability.zombie.olfaction;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.util.measureunit.MeasureUnitConverter;

import java.util.List;

public class ExtremeOlfaction extends Ability {

    private static final int RANGE = 200;

    public ExtremeOlfaction() {
        super("ability.zombie.olfaction.extreme.name", "ability.zombie.olfaction.extreme.description", 150, false);
    }

    @Override
    public List<Ability> getNext() {
        return null;
    }

    @Override
    public void apply(FullUserInfo user, Translator translator) {
        super.apply(user, translator);
        user.setVisibility(RANGE);
    }

    @Override
    protected String formatDescription(Translator translator, String description, MeasureUnit measureUnit) {
        MeasureUnitConverter converter = measureUnit.getConverter();
        int distance = converter.convertDistance(RANGE);
        return String.format(description, distance, measureUnit.getDistanceUnit(translator));
    }
}
