package com.mprojection.db.repository;

import com.mprojection.db.connection.ConnectionHolder;
import com.mprojection.db.extractor.Extractor;
import com.mprojection.db.extractor.InventoryItemExtractor;
import com.mprojection.entity.inventory.InventoryItem;
import com.mprojection.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryItemRepository extends AbstractRepository<InventoryItem> {

    private static final Logger LOGGER = LoggerFactory.getLogger(InventoryItemRepository.class);
    private static final Extractor<InventoryItem> EXTRACTOR = new InventoryItemExtractor();

    @Autowired
    public InventoryItemRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    protected int prepareForInsert(InventoryItem item, PreparedStatement ps) throws SQLException {
        int index = 1;
        ps.setString(index++, item.getItemType());
        ps.setString(index++, item.getItemDescription());
        ps.setString(index++, item.getBarCode());
        ps.setLong(index++, item.getUserId());
        return index;
    }

    @Override
    protected int prepareForUpdate(InventoryItem item, PreparedStatement ps) throws SQLException {
        int index = 1;
        ps.setString(index++, item.getItemType());
        ps.setString(index++, item.getItemDescription());
        ps.setString(index++, item.getBarCode());
        return index;
    }

    @Override
    public InventoryItem add(InventoryItem item) {
        return add(item, get("inventory.insert"));
    }

    @Override
    public void update(InventoryItem item) {
        add(item, get("inventory.update"));
    }

    @Override
    public void delete(long id) {
        deleteById(id, get("inventory.delete"));
    }

    @Override
    public InventoryItem getById(long id) {
        return getById(id, get("inventory.select.by.id"), EXTRACTOR);
    }

    @Override
    public List<InventoryItem> getAll(List<Long> ids) {
        return getAll(ids, get("inventory.select.all.by.ids"), EXTRACTOR);
    }

    public List<InventoryItem> getAllByUserId(long userId) {
        String sql = get("inventory.select.all.by.userId");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, userId);
            return executeQuery(ps, EXTRACTOR);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }


}