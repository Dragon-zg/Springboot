package com.lnnk.jpa.search;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 制造Pageable的工厂类
 @author Lnnk
 @date  2019/5/5 16:36
 */
@SuppressWarnings({"unchecked", "unused"})
public class PageableFactory {
    private final static int DEFAULT_SIZE = 20;
    private final static Sort.Direction DEFAULT_DIRECTION = Sort.Direction.DESC;

    public static Pageable create(Integer page, Integer size) {
        return PageRequest.of(page, null != size && size > 0 ? size : DEFAULT_SIZE);
    }

    public static Pageable create(Integer page, Integer size, int defaultSize) {
        return PageRequest.of(page, null != size && size > 0 ? size : defaultSize);
    }

    public static Pageable create(Integer page, Integer size, Sort sort) {
        return PageRequest.of(page, null != size && size > 0 ? size : DEFAULT_SIZE, sort);
    }

    public static Pageable create(Pageable pageable, Sort.Direction direction, String... properties) {
        return create(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(direction, properties));
    }

    public static Pageable create(Pageable pageable, String... properties) {
        return create(pageable, DEFAULT_DIRECTION, properties);
    }

    public static Pageable create(Pageable pageable, Sort.Order... orders) {
        return create(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(orders));
    }
}
