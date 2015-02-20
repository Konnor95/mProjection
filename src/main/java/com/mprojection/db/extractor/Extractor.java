package com.mprojection.db.extractor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Ihor_Mielientiev
 */
public interface Extractor<T> {

    T extract(ResultSet rs) throws SQLException;
}
