package com.mprojection.service;

import com.mprojection.db.repository.InventoryItemRepository;
import com.mprojection.db.repository.UserRepository;
import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.inventory.InventoryItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryItemRepository repository;

    @Autowired
    private UserRepository userRepository;

    public InventoryItem add(InventoryItem item, Long userId) {
        FullUserInfo user = userRepository.getById(userId);
        if (user == null) {
            return null;
        }
        item.setUserId(user.getId());
        return repository.add(item);
    }

    public void update(InventoryItem item) {
        repository.update(item);
    }

    public void delete(long id) {
        repository.delete(id);
    }

    public InventoryItem getById(long id) {
        return repository.getById(id);
    }

    public List<InventoryItem> getByAllByUserId(long userId) {
        return repository.getAllByUserId(userId);
    }

    public List<InventoryItem> getAll(List<Long> ids) {
        return repository.getAll(ids);
    }

}
