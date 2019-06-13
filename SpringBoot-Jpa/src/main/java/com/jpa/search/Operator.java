package com.jpa.search;

/**
 * @author gelif
 * @date 2019/4/30 16:38
 **/
public enum Operator {
    /** %fieldname% */
    LIKE,
    /** %fieldname */
    LLIKE,
    /** fieldname% */
    RLIKE,
    EQ, GT, LT, GTE, LTE, NE,
    /**
     * BETWEEN_fieldname_LOW, BETWEEN_fieldname_HIGH
     */
    BETWEEN,
    IN, NOTIN, ISNULL, ISNOTNULL
}
