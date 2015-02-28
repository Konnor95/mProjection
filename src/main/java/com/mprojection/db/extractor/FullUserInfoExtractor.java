package com.mprojection.db.extractor;

import com.mprojection.entity.FullUserInfo;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FullUserInfoExtractor implements Extractor<FullUserInfo> {

    @Override
    public FullUserInfo extract(ResultSet rs) throws SQLException {
        FullUserInfo user = new FullUserInfo();
        PublicUserInfoExtractor.setPublicInfo(user, rs);
        user.setFacebookToken(rs.getString("facebookToken"));
        user.setAppleToken(rs.getString("appleToken"));
        user.setXp(rs.getInt("xp"));
        user.setOnline(rs.getBoolean("isOnline"));
        user.setDead(rs.getBoolean("isDead"));
        user.setAttackFactor(rs.getDouble("attackFactor"));
        user.setDefenseFactor(rs.getDouble("defenseFactor"));
        return user;
    }

}
