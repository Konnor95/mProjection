package com.mprojection.entity.ability.soldier.radar;

import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.ability.Ability;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import com.mprojection.util.measureunit.MeasureUnitConverter;

import java.util.List;

public class ProfessionalRadar extends Ability {

    private static final int RANGE = 100;

    public ProfessionalRadar() {
        super("ability.soldier.radar.professional.name", "ability.soldier.radar.professional.description", 150, false);
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
