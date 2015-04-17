package com.mprojection.db.repository;

import com.mprojection.db.connection.ConnectionHolder;
import com.mprojection.db.extractor.Extractor;
import com.mprojection.db.extractor.FullUserInfoExtractor;
import com.mprojection.db.extractor.PublicUserInfoExtractor;
import com.mprojection.db.extractor.TaskExtractor;
import com.mprojection.entity.FullUserInfo;
import com.mprojection.entity.PublicUserInfo;
import com.mprojection.entity.task.Task;
import com.mprojection.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository extends AbstractRepository<FullUserInfo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepository.class);
    private static final Extractor<PublicUserInfo> PUBLIC_USER_INFO_EXTRACTOR = new PublicUserInfoExtractor();
    private static final Extractor<FullUserInfo> FULL_USER_INFO_EXTRACTOR = new FullUserInfoExtractor();

    @Autowired
    private TaskExtractor taskExtractor;

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

    public List<FullUserInfo> addAllWithCoordinates(List<FullUserInfo> users) {
        String sql = get("user.insert.full");
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            for (FullUserInfo user : users) {
                setLocation(ps, prepareForInsert(user, ps), user.getLat(), user.getLng());
                ps.addBatch();
            }
            ps.executeBatch();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            List<Long> ids = new ArrayList<>();
            while (generatedKeys.next()) {
                ids.add(generatedKeys.getLong(1));
            }
            return getAll(ids);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    @Override
    public void update(FullUserInfo user) {
        update(user, get("user.update"));
    }

    public void updateWithFactors(FullUserInfo user) {
        String sql = get("user.update.with.factors");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdateWithFactors(user, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    public void update(PublicUserInfo user) {
        String sql = get("user.update.public");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdate(user, ps);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public FullUserInfo getById(long id) {
        return getById(id, get("user.select.full.by.id"), FULL_USER_INFO_EXTRACTOR);
    }

    public FullUserInfo getByFacebookToken(String facebookToken) {
        String sql = get("user.select.full.by.facebookToken");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, facebookToken);
            List<FullUserInfo> records = executeQuery(ps, FULL_USER_INFO_EXTRACTOR);
            return records.isEmpty() ? null : records.get(0);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    public PublicUserInfo getPublicInfoById(long id) {
        return getById(id, get("user.select.public.by.id"), PUBLIC_USER_INFO_EXTRACTOR);
    }

    public List<FullUserInfo> getAll(List<Long> ids) {
        return getAll(ids, get("user.select.all.by.ids"), FULL_USER_INFO_EXTRACTOR);
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

    public List<Task> getActiveTasks(long userId) {
        String sql = get("task.select.active");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, userId);
            return executeQuery(ps, taskExtractor);
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    public Task getActiveTask(long userId, String taskId) {
        String sql = get("task.select.active.by.id");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setLong(1, userId);
            ps.setString(2, taskId);
            return executeQuery(ps, taskExtractor).get(0);
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
            ps.setDouble(3, user.getLat());
            ps.setDouble(4, user.getLng());
            ps.setInt(5, user.getVisibility());
            ps.setLong(6, user.getId());
            ps.setDouble(7, user.getLat());
            ps.setDouble(8, user.getLng());
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

    public void addTask(Task task) {
        String sql = get("task.add");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, task.getId());
            ps.setLong(2, task.getExecutor());
            ps.setLong(3, task.getTarget());
            ps.setString(4, task.getHash());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    public void completeTask(String taskId) {
        String sql = get("task.complete");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DAOException(getMessage(sql), e);
        }
    }

    public PublicUserInfo findNearestUserOfDifferentGender(long userId) {
        PublicUserInfo user = getPublicInfoById(userId);
        String sql = get("user.findNearestUserOfDifferentGender");
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setDouble(1, user.getLat());
            ps.setDouble(2, user.getLng());
            ps.setInt(3, user.getVisibility());
            ps.setLong(4, user.getId());
            ps.setBoolean(5, !user.isGender());
            ps.setDouble(6, user.getLat());
            ps.setDouble(7, user.getLng());
            return executeQuery(ps, PUBLIC_USER_INFO_EXTRACTOR).get(0);
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
        ps.setBoolean(++index, user.isGender());
        ps.setString(++index, user.getLogin());
        ps.setString(++index, user.getFacebookToken());
        ps.setString(++index, user.getAppleToken());
        ps.setString(++index, user.getLang());
        ps.setShort(++index, user.getTypeOrdinal());
        int defaultVisibility = user.getType().getDefaultVisibility();
        int visibility = user.getVisibility() < defaultVisibility ? defaultVisibility : user.getVisibility();
        ps.setInt(++index, visibility);
        int defaultAttack = user.getType().getDefaultAttack();
        int attack = user.getAttack() < defaultAttack ? defaultAttack : user.getAttack();
        ps.setInt(++index, attack);
        int defaultDefense = user.getType().getDefaultDefense();
        int defense = user.getDefense() < defaultDefense ? defaultDefense : user.getDefense();
        ps.setInt(++index, defense);
        return index;
    }

    @Override
    protected int prepareForUpdate(FullUserInfo user, PreparedStatement ps) throws SQLException {
        int index = prepareForInsert(ps, user);
        ps.setBoolean(++index, user.isOnline());
        ps.setBoolean(++index, user.isDead());
        ps.setLong(++index, user.getId());
        return index;
    }

    private int prepareForUpdateWithFactors(FullUserInfo user, PreparedStatement ps) throws SQLException {
        int index = prepareForInsert(ps, user);
        ps.setBoolean(++index, user.isOnline());
        ps.setBoolean(++index, user.isDead());
        ps.setDouble(++index, user.getAttackFactor());
        ps.setDouble(++index, user.getDefenseFactor());
        ps.setDouble(++index, user.getVisibilityFactor());
        ps.setDouble(++index, user.getHealthFactor());
        ps.setLong(++index, user.getId());
        return index;
    }

    private int prepareForUpdate(PublicUserInfo user, PreparedStatement ps) throws SQLException {
        int index = 0;
        ps.setInt(++index, user.getHp());
        boolean isDead = user.getHp() < 0;
        ps.setBoolean(++index, isDead);
        ps.setLong(++index, user.getId());
        return index;
    }

    private int prepareForInsert(PreparedStatement ps, FullUserInfo user) throws SQLException {
        int index = 0;
        ps.setString(++index, user.getFirstName());
        ps.setString(++index, user.getLastName());
        ps.setBoolean(++index, user.isGender());
        ps.setString(++index, user.getLogin());
        ps.setString(++index, user.getFacebookToken());
        ps.setString(++index, user.getAppleToken());
        ps.setString(++index, user.getLang());
        index = setLocation(ps, index, user.getLat(), user.getLng());
        ps.setInt(++index, user.getHp());
        ps.setInt(++index, user.getXp());
        ps.setShort(++index, user.getTypeOrdinal());
        int defaultVisibility = user.getType().getDefaultVisibility();
        int visibility = user.getVisibility() < defaultVisibility ? defaultVisibility : user.getVisibility();
        ps.setInt(++index, visibility);
        int defaultAttack = user.getType().getDefaultAttack();
        int attack = user.getAttack() < defaultAttack ? defaultAttack : user.getAttack();
        ps.setInt(++index, attack);
        int defaultDefense = user.getType().getDefaultDefense();
        int defense = user.getDefense() < defaultDefense ? defaultDefense : user.getDefense();
        ps.setInt(++index, defense);
        return index;
    }
}
