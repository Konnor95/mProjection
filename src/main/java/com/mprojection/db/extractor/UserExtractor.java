package com.mprojection.db.extractor;

import com.mprojection.entity.User;
import com.mprojection.entity.UserType;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserExtractor implements Extractor<User> {

    @Override
    public User extract(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setLogin(rs.getString("login"));
        user.setFacebookToken(rs.getString("facebookToken"));
        user.setAppleToken(rs.getString("appleToken"));
        user.setLat(rs.getDouble("lat"));
        user.setLng(rs.getDouble("lng"));
        user.setHp(rs.getInt("hp"));
        user.setLevel(rs.getShort("level"));
        user.setType(UserType.define(rs.getShort("type")));
        return user;
    }
}
