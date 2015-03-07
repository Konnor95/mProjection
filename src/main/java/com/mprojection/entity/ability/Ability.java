package com.mprojection.entity.ability;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mprojection.entity.FullUserInfo;
import com.mprojection.exception.LogicalException;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;

import java.util.List;

public abstract class Ability {

    private String id = "";
    private String nameKey;
    private String descriptionKey;
    private int price;
    private boolean isAvailableByDefault;

    protected Ability(String nameKey, String descriptionKey, int price, boolean isAvailableByDefault) {
        this.nameKey = nameKey;
        this.descriptionKey = descriptionKey;
        this.price = price;
        this.isAvailableByDefault = isAvailableByDefault;
    }

    public String getId() {
        return id;
    }

    public void setId(String prefix, short id) {
        this.id = prefix + "." + id;
        short tempId = 1;
        List<Ability> next = getNext();
        if (next == null) {
            return;
        }
        for (Ability ability : getNext()) {
            ability.setId(this.id, tempId++);
        }
    }

    public String getName(Translator translator, String locale) {
        return translator.translate(nameKey, locale);
    }

    public String getDescription(Translator translator, MeasureUnit measureUnit, String locale) {
        return formatDescription(translator, translator.translate(descriptionKey, locale), measureUnit, locale);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isAvailableByDefault() {
        return isAvailableByDefault;
    }

    @SuppressWarnings("unused")
    protected String formatDescription(Translator translator, String description, MeasureUnit measureUnit, String locale) {
        return description;
    }

    @JsonProperty
    public abstract List<Ability> getNext();

    public void apply(FullUserInfo user, Translator translator) {
        int left = user.getXp() - price;
        if (left < 0) {
            throw new LogicalException(translator.translate("exception.logical.ability.insufficientFunds", user.getLang()));
        }
        user.setXp(left);
    }

    public Ability get(String id) {
        if (id.equals(this.id)) {
            return this;
        }
        List<Ability> next = getNext();
        if (next == null) {
            return null;
        }
        for (Ability ability : next) {
            Ability found = ability.get(id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }
}
