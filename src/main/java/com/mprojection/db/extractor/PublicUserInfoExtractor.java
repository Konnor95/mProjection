package com.mprojection.db.extractor;

import com.mprojection.entity.PublicUserInfo;
import com.mprojection.entity.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PublicUserInfoExtractor implements Extractor<PublicUserInfo> {

    @Override
    public PublicUserInfo extract(ResultSet rs) throws SQLException {
        PublicUserInfo user = new PublicUserInfo();
        setPublicInfo(user, rs);
        return user;
    }

    static void setPublicInfo(PublicUserInfo user, ResultSet rs) throws SQLException {
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setGender(rs.getBoolean("gender"));
        user.setLogin(rs.getString("login"));
        user.setAppleToken(rs.getString("appleToken"));
        user.setLang(rs.getString("lang"));
        user.setLat(rs.getDouble("lat"));
        user.setLng(rs.getDouble("lng"));
        user.setHp(rs.getInt("hp"));
        user.setType(UserType.define(rs.getShort("type")));
        user.setVisibility(rs.getInt("visibility"));
        user.setAttack(rs.getInt("attack"));
        user.setDefense(rs.getInt("defense"));
        user.setAttackFactor(rs.getFloat("attackFactor"));
        user.setDefenseFactor(rs.getFloat("defenseFactor"));
        user.setVisibilityFactor(rs.getFloat("visibilityFactor"));
        user.setHealthFactor(rs.getFloat("healthFactor"));
        Double dist;
        try {
            dist = rs.getDouble("dist");
        } catch (SQLException e) {
            dist = null;
        }
        user.setDistance(dist);
    }
}
