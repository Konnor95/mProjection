package com.mprojection.db.repository;

import com.mprojection.db.connection.ConnectionHolder;
import com.mprojection.db.extractor.Extractor;
import com.mprojection.db.extractor.FullUserInfoExtractor;
import com.mprojection.db.extractor.PublicUserInfoExtractor;
import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserRepository extends AbstractRepository<FullUserInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private static final Extractor<PublicUserInfo> PUBLIC_USER_INFO_EXTRACTOR = new PublicUserInfoExtractor();
    private static final Extractor<FullUserInfo> FULL_USER_INFO_EXTRACTOR = new FullUserInfoExtractor();


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
    public FullUserInfo add(FullUserInfo user) {
        return add(user, get("user.insert"));
    }

    @Override
    public void update(FullUserInfo user) {
        update(user, get("user.update"));
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public FullUserInfo getById(long id) {
        return getById(id, get("user.select.full.by.id"), FULL_USER_INFO_EXTRACTOR);
    }

    public PublicUserInfo getPublicInfoById(long id) {
        return getById(id, get("user.select.public.by.id"), PUBLIC_USER_INFO_EXTRACTOR);
    }

    @Override
    public List<FullUserInfo> getAll(List<Long> ids) {
        return null;
    }

    public List<String> getAbilitiesIds(long userId) {
        String sql = get("user.select.abilities.by.id");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, userId);
            return getList(ps, String.class);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    public List<PublicUserInfo> getNearest(long userId) {
        PublicUserInfo user = getPublicInfoById(userId);
        String sql = get("user.select.nearest");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, user.getLat());
            ps.setDouble(2, user.getLng());
            ps.setInt(3, user.getVisibility());
            ps.setLong(4, user.getId());
            ps.setDouble(5, user.getLat());
            ps.setDouble(6, user.getLng());
            return executeQuery(ps, PUBLIC_USER_INFO_EXTRACTOR);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    public FullUserInfo addAbility(long userId, String abilityId) {
        String sql = get("user.addAbility");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setString(2, abilityId);
            ps.executeUpdate();
            return getById(userId);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    @Override
    protected int prepareForInsert(FullUserInfo user, PreparedStatement ps) throws SQLException {
        int index = 0;
        ps.setString(++index, user.getFirstName());
        ps.setString(++index, user.getLastName());
        ps.setString(++index, user.getLogin());
        ps.setString(++index, user.getFacebookToken());
        ps.setString(++index, user.getAppleToken());
        index = setLocation(ps, index, user.getLat(), user.getLng());
        ps.setInt(++index, user.getHp());
        ps.setInt(++index, user.getXp());
        ps.setShort(++index, user.getTypeOrdinal());
        int defaultVisibility = user.getType().getDefaultVisibility();
        int visibility = user.getVisibility() < defaultVisibility ? defaultVisibility : user.getVisibility();
        ps.setInt(++index, visibility);
        return index;
    }

    @Override
    protected int prepareForUpdate(FullUserInfo user, PreparedStatement ps) throws SQLException {
        int index = prepareForInsert(user, ps);
        ps.setBoolean(++index, user.isOnline());
        ps.setBoolean(++index, user.isDead());
        ps.setDouble(++index, user.getAttackFactor());
        ps.setDouble(++index, user.getDefenseFactor());
        ps.setLong(++index, user.getId());
        return index;
    }
}
