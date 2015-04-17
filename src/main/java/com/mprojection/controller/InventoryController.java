package com.mprojection.controller;

import com.mprojection.entity.inventory.InventoryItem;
import com.mprojection.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/inventory/")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public InventoryItem add(InventoryItem item, long userId) {
        return inventoryService.add(item, userId);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void update(InventoryItem item) {
        inventoryService.update(item);
    }

    @RequestMapping(value = "{id}/", method = RequestMethod.DELETE)
    public void delete(@PathVariable long id) {
        inventoryService.delete(id);
    }

    @RequestMapping(value = "{userId}/", method = RequestMethod.GET)
    public List<InventoryItem> getAllByUserId(@PathVariable long userId) {
        return inventoryService.getByAllByUserId(userId);
    }

}
