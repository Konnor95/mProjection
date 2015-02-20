package com.mprojection.db.repository;

import com.mprojection.db.connection.ConnectionHolder;
import com.mprojection.db.extractor.Extractor;
import com.mprojection.db.extractor.UserExtractor;
import com.mprojection.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserRepository extends AbstractRepository<User> {

    private static final Extractor<User> EXTRACTOR = new UserExtractor();

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    @Autowired
    public UserRepository(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public User add(User user) {
        return add(user, get("user.insert"));
    }

    @Override
    public void update(User user) {
        update(user, get("user.update"));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public User getById(long id) {
        return getById(id, get("user.select.by.id"), EXTRACTOR);
    }

    @Override
    public List<User> getAll(List<Long> ids) {
        return null;
    }

    @Override
    protected int prepareForInsert(User user, PreparedStatement ps) throws SQLException {
        int index = 0;
        ps.setString(++index, user.getFirstName());
        ps.setString(++index, user.getLastName());
        ps.setString(++index, user.getLogin());
        ps.setString(++index, user.getFacebookToken());
        ps.setString(++index, user.getAppleToken());
        index = setLocation(ps, index, user.getLat(), user.getLng());
        ps.setInt(++index, user.getHp());
        ps.setShort(++index, user.getLevel());
        ps.setShort(++index, user.getTypeOrdinal());
        return index;
    }

    @Override
    protected int prepareForUpdate(User user, PreparedStatement ps) throws SQLException {
        int index = prepareForInsert(user, ps);
        ps.setLong(++index, user.getId());
        return index;
    }
}
