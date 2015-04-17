package com.mprojection.db.extractor;

import com.mprojection.entity.inventory.InventoryItem;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class InventoryItemExtractor implements Extractor<InventoryItem> {

    @Override
    public InventoryItem extract(ResultSet rs) throws SQLException {
        InventoryItem item = new InventoryItem();
        item.setId(rs.getLong("id"));
        item.setItemType(rs.getString("itemType"));
        item.setItemDescription(rs.getString("itemDescription"));
        item.setBarCode(rs.getString("barCode"));
        item.setUserId(rs.getLong("userId"));
        return item;
    }

}
