package com.mprojection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the field contains complex object
 * that cannot be simply extracted from {@code ResultSet}
 * and must be extracted separately.
 *
 * @see java.sql.ResultSet
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Extract {

    /**
     * Prefix of the columns' labels that contains values of this object.
     *
     * @return prefix of the columns' labels that contains values of this object
     */
    String value() default "";
}
