package com.mprojection.service;

import com.mprojection.annotation.EnableTransaction;
import com.mprojection.db.repository.UserRepository;
import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.entity.UserType;
import com.mprojection.entity.ability.Abilities;
import com.mprojection.entity.ability.Ability;
import com.mprojection.entity.ability.UserAbility;
import com.mprojection.entity.task.Task;
import com.mprojection.entity.task.Tasks;
import com.mprojection.entity.task.UserTask;
import com.mprojection.exception.LogicalException;
import com.mprojection.util.GeoUtil;
import com.mprojection.util.Translator;
import com.mprojection.util.measureunit.MeasureUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mprojection.util.GeoUtil.generateRandomPointWithinArea;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Abilities abilities;

    @Autowired
    private Translator translator;

    @Autowired
    private Tasks tasks;

    public FullUserInfo add(FullUserInfo user) {
        FullUserInfo existingUser = repository.getByFacebookToken(user.getFacebookToken());
        if (existingUser != null) {
            return existingUser;
        }
        return repository.add(user);
    }

    @EnableTransaction
    public void update(FullUserInfo user) {
        repository.update(user);
    }

    public FullUserInfo updateWithFactors(FullUserInfo user, MeasureUnit measureUnit) {
        repository.updateWithFactors(user);
        return get(user.getId(), measureUnit);
    }

    public FullUserInfo updateAndReturn(FullUserInfo user, MeasureUnit measureUnit) {
        update(user);
        return get(user.getId(), measureUnit);
    }

    public FullUserInfo get(long id, MeasureUnit measureUnit) {
        FullUserInfo user = repository.getById(id);
        List<UserAbility> userAbilities = abilities.define(user.getType(), getAbilitiesIds(id), measureUnit, user.getLang());
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
            throw new LogicalException(translator.translate("exception.logical.ability.notFound", user.getLang()));
        }
        if (userAbility.isAvailable()) {
            throw new LogicalException(translator.translate("exception.logical.ability.isAlreadyAvailable", user.getLang()));
        }
        Ability ability = abilities.define(user.getType(), abilityId);
        ability.apply(user, translator);
        userAbility.setAvailable(true);
        repository.update(user);
        repository.addAbility(userId, abilityId);
        return user;
    }

    public PublicUserInfo attack(PublicUserInfo user1, PublicUserInfo user2) {
        int damage = (int) (user1.getAttack() * user1.getAttackFactor() - user2.getDefense() * user2.getDefenseFactor());
        if (damage < 0) {
            damage = 0;
        }
        int hp2 = user2.getHp() - damage;
        if (hp2 < 0) {
            hp2 = 0;
        }
        user2.setHp(hp2);
        repository.update(user2);
        return user2;
    }

    public List<UserTask> getActiveTasks(FullUserInfo user) {
        return tasks.define(repository.getActiveTasks(user.getId()), user.getLang());
    }

    public Task getActiveTask(FullUserInfo user, String taskId) {
        return repository.getActiveTask(user.getId(), taskId);
    }

    public void addTask(Task task) {
        repository.addTask(task);
    }

    public void completeTask(String taskId) {
        repository.completeTask(taskId);
    }

    public PublicUserInfo findNearestUser(long userId) {
        return repository.findNearestUser(userId);
    }

    public PublicUserInfo findNearestUserOfDifferentGender(long userId) {
        return repository.findNearestUserOfDifferentGender(userId);
    }

    @EnableTransaction
    public List<PublicUserInfo> generateRandomUsers(double lat, double lng, int radius, int count) {
        Random random = new Random();
        List<FullUserInfo> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            GeoUtil.Point point = generateRandomPointWithinArea(random, radius, 5, lat, lng);
            FullUserInfo user = new FullUserInfo();
            user.setLang("en");
            user.setFacebookToken("facebook");
            user.setAppleToken("<927d54e5 88a56875 bed2f8a7 490278c0 c28c573e 8c6db3a8 ee2d8351 5bf31048>");
            user.setFirstName("firstName" + i);
            user.setLastName("lastName" + i);
            user.setGender(false);
            user.setLogin("login" + i);
            user.setType(UserType.ZOMBIE);
            user.setLat(point.getX());
            user.setLng(point.getY());
            users.add(user);
        }
        List<PublicUserInfo> publicUserInfos = new ArrayList<>();
        for (FullUserInfo info : repository.addAllWithCoordinates(users)) {
            publicUserInfos.add(info);
        }
        return publicUserInfos;
    }


    private List<String> getAbilitiesIds(long id) {
        return repository.getAbilitiesIds(id);
    }


}
