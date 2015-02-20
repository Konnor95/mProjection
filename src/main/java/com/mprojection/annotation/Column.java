package com.mprojection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Associates a field and a column in {@code ResultSet} object
 * @see java.sql.ResultSet
 */
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {

    /**
     * Column name. Should be specified only if names of the filed and the column mismatch.
     *
     * @return column name
     */
    String value() default "";

}
