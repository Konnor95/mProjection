package com.mprojection.service;

import com.mprojection.annotation.EnableTransaction;
import com.mprojection.db.repository.UserRepository;
import com.mprojection.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public User add(User user) {
        return repository.add(user);
    }

    @EnableTransaction
    public void update(User user) {
        repository.update(user);
    }

    public User updateAndReturn(User user) {
        update(user);
        return get(user.getId());
    }

    public User get(long id) {
        return repository.getById(id);
    }

}
