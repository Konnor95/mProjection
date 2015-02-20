package com.mprojection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Sets the prefix for the columns' labels of {@code ResultSet}.
 *
 * @see java.sql.ResultSet
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Prefix {

    /**
     * The prefix for the columns' labels
     *
     * @return the prefix for the columns' labels
     */
    String value();

}
