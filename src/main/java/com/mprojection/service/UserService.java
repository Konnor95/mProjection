package com.mprojection.service;

import com.mprojection.annotation.EnableTransaction;
import com.mprojection.db.repository.UserRepository;
import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.entity.ability.Abilities;
import com.mprojection.entity.ability.Ability;
import com.mprojection.entity.ability.UserAbility;
import com.mprojection.exception.LogicalException;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Abilities abilities;

    @Autowired
    private Translator translator;

    public FullUserInfo add(FullUserInfo user) {
        return repository.add(user);
    }

    @EnableTransaction
    public void update(FullUserInfo user) {
        repository.update(user);
    }

    public FullUserInfo updateAndReturn(FullUserInfo user, MeasureUnit measureUnit) {
        update(user);
        return get(user.getId(), measureUnit);
    }

    public FullUserInfo get(long id, MeasureUnit measureUnit) {
        FullUserInfo user = repository.getById(id);
        List<UserAbility> userAbilities = abilities.define(user.getType(), getAbilitiesIds(id), measureUnit);
        user.setAbilities(userAbilities);
        return user;
    }

    public PublicUserInfo getPublicInfo(long id) {
        return repository.getPublicInfoById(id);
    }

    public List<PublicUserInfo> getNearest(long id) {
        return repository.getNearest(id);
    }

    // TODO check if ability is accessible
    public FullUserInfo addAbility(long userId, MeasureUnit measureUnit, String abilityId) {
        FullUserInfo user = get(userId, measureUnit);
        UserAbility userAbility = abilities.define(user, abilityId);
        if (userAbility == null) {
            throw new LogicalException(translator.translate("exception.logical.ability.notFound"));
        }
        if (userAbility.isAvailable()) {
            throw new LogicalException(translator.translate("exception.logical.ability.isAlreadyAvailable"));
        }
        Ability ability = abilities.define(user.getType(), abilityId);
        ability.apply(user, translator);
        userAbility.setAvailable(true);
        repository.update(user);
        repository.addAbility(userId, abilityId);
        return user;
    }

    private List<String> getAbilitiesIds(long id) {
        return repository.getAbilitiesIds(id);
    }

}
