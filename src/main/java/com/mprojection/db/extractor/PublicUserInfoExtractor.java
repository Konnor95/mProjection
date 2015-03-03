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
        user.setLogin(rs.getString("login"));
        user.setLat(rs.getDouble("lat"));
        user.setLng(rs.getDouble("lng"));
        user.setHp(rs.getInt("hp"));
        user.setType(UserType.define(rs.getShort("type")));
        user.setVisibility(rs.getInt("visibility"));
        user.setAbilityAttackFactor(rs.getFloat("abilityAttackFactor"));
        user.setAbilityDefenseFactor(rs.getFloat("abilityDefenseFactor"));
        user.setTemperatureAttackFactor(rs.getFloat("temperatureAttackFactor"));
        user.setTemperatureDefenseFactor(rs.getFloat("temperatureDefenseFactor"));
        user.setSunAttackFactor(rs.getFloat("sunAttackFactor"));
        user.setSunDefenseFactor(rs.getFloat("sunDefenseFactor"));
        Double dist;
        try {
            dist = rs.getDouble("dist");
        } catch (SQLException e) {
            dist = null;
        }
        user.setDistance(dist);
    }
}
