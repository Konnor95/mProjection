package com.mprojection.entity.ability.scientist.tracker;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.UserType;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.util.measureunit.MeasureUnitConverter;

import java.util.ArrayList;
import java.util.List;

public class SimpleTracker extends Ability {

    private static final List<Ability> NEXT = new ArrayList<>();
    private static final int RANGE = UserType.SCIENTIST.getDefaultVisibility();

    static {
        NEXT.add(new ImprovedTracker());
    }

    public SimpleTracker() {
        super("ability.scientist.tracker.simple.name", "ability.scientist.tracker.simple.description", 0, true);
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
