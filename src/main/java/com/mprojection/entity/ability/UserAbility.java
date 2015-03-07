package com.mprojection.entity.ability;

import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;

import java.util.ArrayList;
import java.util.List;

public class UserAbility {

    private String id;
    private String name;
    private String description;
    private boolean isAvailable;
    private int price;
    private List<UserAbility> next = new ArrayList<>();

    public UserAbility(Ability ability, List<String> ids, Translator translator, MeasureUnit measureUnit, String locale) {
        id = ability.getId();
        name = ability.getName(translator, locale);
        description = ability.getDescription(translator, measureUnit, locale);
        isAvailable = ability.isAvailableByDefault() || ids.contains(id);
        price = ability.getPrice();
        List<Ability> nextAbilities = ability.getNext();
        if (nextAbilities == null) {
            return;
        }
        for (Ability a : nextAbilities) {
            next.add(new UserAbility(a, ids, translator, measureUnit, locale));
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<UserAbility> getNext() {
        return next;
    }

    public void setNext(List<UserAbility> next) {
        this.next = next;
    }

    public UserAbility get(String id) {
        if (id.equals(this.id)) {
            return this;
        }
        List<UserAbility> next = getNext();
        if (next == null) {
            return null;
        }
        for (UserAbility ability : next) {
            UserAbility found = ability.get(id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

}
