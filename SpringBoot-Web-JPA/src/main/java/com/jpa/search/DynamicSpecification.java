package com.jpa.search;

import com.google.common.collect.Lists;
import com.web.enums.ExceptionCode;
import com.web.exception.CustomizedException;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 构造 {@link org.springframework.data.jpa.domain.Specification} 的工具类
 * @author gelif
 * @date 2019/4/30 16:41
 **/
@Log4j2
@SuppressWarnings("unchecked")
public class DynamicSpecification {
    /**
     * 从 {@link com.geewit.data.jpa.search.SearchFilter} 构造 {@link org.springframework.data.jpa.domain.Specification}
     * @param  filters {@link SearchFilter}
     * @return {@link org.springframework.data.jpa.domain.Specification}
     */
    public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(filters != null && !filters.isEmpty()) {
                log.debug("filters != null");
                List<Predicate> predicates = Lists.newArrayList();
                for(SearchFilter filter : filters) {
                    if(log.isDebugEnabled()) {
                        log.debug("filter.fieldName = " + filter.fieldName());
                        log.debug("filter.values = " + StringUtils.join(filter.values(), ","));
                        log.debug("filter.operator = " + filter.operator());
                        log.debug("root.model = " + root.getModel().getName());
                    }
                    Path path;
                    if(StringUtils.contains(filter.fieldName(), '$')) {
                        String[] fields = StringUtils.split(filter.fieldName(), '$');
                        path = root.<String>get(fields[0]);
                        for (int i = 1; i < fields.length; i++) {
                            path = path.get(fields[i]);
                            if(path == null) {
                                break;
                            }
                        }
                    } else {
                        path = root.<String>get(filter.fieldName());
                    }
                    if(path == null) {
                        break;
                    }

                    switch (filter.operator()) {
                        case EQ: {
                            log.debug("case EQ");
                            if(filter.values() != null && filter.values().length > 0) {
                                Object value = parseValue(path, filter.values()[0], false, null);
                                if(value != null) {
                                    predicates.add(criteriaBuilder.equal(path, value));
                                }
                            }
                            break;
                        }
                        case NE: {
                            log.debug("case NE");
                            if(filter.values() != null && filter.values().length > 0) {
                                Object value = parseValue(path, filter.values()[0], false, null);
                                if(value != null) {
                                    predicates.add(criteriaBuilder.notEqual(path, value));
                                }
                            }
                            break;
                        }
                        case LIKE: {
                            log.debug("case LIKE");
                            if(filter.values() != null && filter.values().length > 0) {
                                if(StringUtils.isNotEmpty(filter.values()[0].toString())) {
                                    predicates.add(criteriaBuilder.like(path, "%" + filter.values()[0].toString() + "%"));
                                }
                            }
                            break;
                        }
                        case LLIKE: {
                            log.debug("case LLIKE");
                            if(filter.values() != null && filter.values().length > 0) {
                                if(StringUtils.isNotEmpty(filter.values()[0].toString())) {
                                    predicates.add(criteriaBuilder.like(path, "%" + filter.values()[0].toString()));
                                }
                            }
                            break;
                        }
                        case RLIKE: {
                            log.debug("case RLIKE");
                            if(filter.values() != null && filter.values().length > 0) {
                                if(StringUtils.isNotEmpty(filter.values()[0].toString())) {
                                    predicates.add(criteriaBuilder.like(path, filter.values()[0].toString() + "%"));
                                }
                            }
                            break;
                        }
                        case GT: {
                            log.debug("case GT");
                            if(filter.values() != null && filter.values().length > 0) {
                                Object value = parseValue(path, filter.values()[0], true, null);
                                if(value instanceof Comparable) {
                                    if(StringUtils.isNotEmpty(filter.values()[0].toString())) {
                                        predicates.add(criteriaBuilder.greaterThan(path, (Comparable) value));
                                    }
                                }
                            }
                            break;
                        }
                        case GTE: {
                            log.debug("case GTE");
                            if(filter.values() != null && filter.values().length > 0) {
                                Object value = parseValue(path, filter.values()[0], true, null);
                                if(value instanceof Comparable) {
                                    if(StringUtils.isNotEmpty(filter.values()[0].toString())) {
                                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, (Comparable) value));
                                    }
                                }
                            }
                            break;
                        }
                        case LT: {
                            log.debug("case LT");
                            if(filter.values() != null && filter.values().length > 0) {
                                Object value = parseValue(path, filter.values()[0], true, null);
                                if(value instanceof Comparable) {
                                    if(StringUtils.isNotEmpty(filter.values()[0].toString())) {
                                        predicates.add(criteriaBuilder.lessThan(path, (Comparable) value));
                                    }
                                }
                            }
                            break;
                        }
                        case LTE: {
                            log.debug("case LTE");
                            if(filter.values() != null && filter.values().length > 0) {
                                Object value = parseValue(path, filter.values()[0], true, null);
                                if(value instanceof Comparable) {
                                    if(StringUtils.isNotEmpty(filter.values()[0].toString())) {
                                        predicates.add(criteriaBuilder.lessThanOrEqualTo(path, (Comparable) value));
                                    }
                                }
                            }
                            break;
                        }
                        case BETWEEN: {
                            log.debug("case BETWEEN");
                            if(filter.values().length == 2) {
                                Object value1 = parseValue(path, filter.values()[0], true, BetweenType.LOW);
                                Object value2 = parseValue(path, filter.values()[1], true, BetweenType.HIGH);
                                if(value1 != null && value2 != null) {
                                    if(value1 instanceof Comparable && value2 instanceof Comparable) {
                                        predicates.add(criteriaBuilder.between(path, ((Comparable)value1), ((Comparable)value2)));
                                    }
                                } else if(value1 != null) {
                                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(path, ((Comparable)value1)));
                                } else if(value2 != null) {
                                    predicates.add(criteriaBuilder.lessThanOrEqualTo(path, ((Comparable)value2)));
                                }
                            }
                            break;
                        }
                        case IN: {
                            log.debug("case IN");
                            if(filter.values() != null && filter.values().length > 0) {
                                predicates.add(path.in((Object[]) StringUtils.split(filter.values()[0].toString(), ',')));
                            }
                            break;
                        }
                        case NOTIN: {
                            log.debug("case NOTIN");
                            if(filter.values() != null && filter.values().length > 0) {
                                predicates.add(criteriaBuilder.not(path.in((Object[]) StringUtils.split(filter.values()[0].toString(), ','))));
                            }
                            break;
                        }
                        case ISNULL: {
                            log.debug("case IS NULL");
                            predicates.add(criteriaBuilder.isNull(path));
                            break;
                        }
                        case ISNOTNULL : {
                            log.debug("case IS NOT NULL");
                            predicates.add(criteriaBuilder.isNotNull(path));
                            break;
                        }
                        default: {
                            throw new CustomizedException(ExceptionCode.PARAM_ERROR);
                        }
                    }
                }

                if(!predicates.isEmpty()) {
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }
            } else {
                log.debug("filters == null");
            }

            return criteriaBuilder.conjunction();
        };
    }

    /**
     * 合并多个 {@link org.springframework.data.jpa.domain.Specification}
     * @param firstSpecification 第一个{@link org.springframework.data.jpa.domain.Specification}
     * @param specifications 需要合并的{@link org.springframework.data.jpa.domain.Specification}
     * @return {@link org.springframework.data.jpa.domain.Specification}
     */
    public static <T>Specification<T> mergeSpecification(Specification<T> firstSpecification, Specification<T>... specifications) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = Lists.newArrayList(firstSpecification.toPredicate(root, criteriaQuery, criteriaBuilder));
            if(specifications != null) {
                for(Specification<T> specification : specifications) {
                    predicates.add(specification.toPredicate(root, criteriaQuery, criteriaBuilder));
                }
            }
            if(!predicates.isEmpty()) {
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
            return criteriaBuilder.conjunction();
        };
    }

    public static Object parseValue(Class pathClass, Object objValue, boolean needComparable, BetweenType betweenType) {
        if(pathClass == null || objValue == null) {
            return null;
        }
        String strValue = objValue.toString();
        if(String.class.isAssignableFrom(pathClass)) {
            return objValue;
        } else if(pathClass.isEnum()) {
            log.debug("path is enum");
            try {
                return Enum.valueOf(pathClass, strValue);
            } catch (Exception e) {
                log.warn(e.getMessage(), e);
                return null;
            }
        } else if(Date.class.isAssignableFrom(pathClass)) {
            try {
                if(betweenType != null) {
                    switch (betweenType) {
                        case LOW: {
                            return fromDate(strValue);
                        }
                        case HIGH: {
                            return toDate(strValue);
                        }
                        default: {
                            throw new CustomizedException(ExceptionCode.PARAM_ERROR);
                        }
                    }
                } else {
                    return date(strValue);
                }
            } catch (ParseException e) {
                log.warn(e.getMessage(), e);
                return null;
            }
        } else if(Number.class.isAssignableFrom(pathClass)) {
            return org.springframework.util.NumberUtils.parseNumber(strValue, pathClass);
        }
        if(needComparable && !Comparable.class.isAssignableFrom(pathClass)) {
            return null;
        }
        return objValue;
    }

    private static Object parseValue(Path path, Object objValue, boolean needComparable, BetweenType betweenType) {
        if(path == null || objValue == null) {
            return null;
        }
        Class pathClass = path.getJavaType();

        return parseValue(pathClass, objValue, needComparable, betweenType);
    }

    private static final String[] PATTERNS = {"yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "HH:mm:ss"};

    private static Date date(String date) throws ParseException {
        return org.apache.commons.lang3.time.DateUtils.parseDateStrictly(date, PATTERNS);
    }

    private static Date betweenDate(String date, BetweenType betweenType) throws ParseException {
        Date fromDate;
        if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}\\s+\\d{2}:\\d{2}:\\d{2}", date)) {
            return date(date);
        } else if (Pattern.matches("\\d{4}-\\d{2}-\\d{2}", date)) {
            fromDate = date(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fromDate);
            switch (betweenType) {
                case LOW: {
                    calendar.set(Calendar.HOUR_OF_DAY, 0);
                    calendar.set(Calendar.MINUTE, 0);
                    calendar.set(Calendar.SECOND, 0);
                    calendar.set(Calendar.MILLISECOND, 0);
                    break;
                }
                case HIGH: {
                    calendar.set(Calendar.HOUR_OF_DAY, 23);
                    calendar.set(Calendar.MINUTE, 59);
                    calendar.set(Calendar.SECOND, 59);
                    calendar.set(Calendar.MILLISECOND, 999);
                    break;
                }
                default: {
                    throw new CustomizedException(ExceptionCode.PARAM_ERROR);
                }
            }

            return calendar.getTime();
        }

        return null;
    }

    /**
     * 转换 {@link String} 类型的date为 {@link Date}类型 时分秒为00:00:00
     *
     * @param date {@link java.util.Date} 类型
     */
    private static Date fromDate(String date) throws ParseException {
        return betweenDate(date, BetweenType.LOW);
    }

    /**
     * 转换 {@link String} 类型的date为 {@link Date}类型 时分秒为23:59:59
     *
     * @param date {@link java.util.Date} 类型
     */
    private static Date toDate(String date) throws ParseException {
        return betweenDate(date, BetweenType.HIGH);
    }
}
