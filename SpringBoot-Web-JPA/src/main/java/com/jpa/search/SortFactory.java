package com.jpa.search;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.Collections;
import java.util.List;

/**
 制造Sort的工厂类
 @author gelif
 @since  2015-5-18
 */
public class SortFactory {
    private final static Sort.Direction DEFAULT_DIRECTION = Sort.Direction.DESC;

    public static Sort create(String sortProperty, Sort.Direction direction) {
        if(sortProperty == null) {
            return null;
        }
        return new Sort(direction, sortProperty);
    }

    public static Sort create(String sortProperty, String order) {
        if(sortProperty == null) {
            return null;
        }
        Sort.Direction direction = (StringUtils.isNotBlank(order) && Sort.Direction.DESC.name().equalsIgnoreCase(order)) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return new Sort(direction, sortProperty);
    }

    public static Sort create(Sort sort, String sortPropterty, Sort.Direction direction) {
        List<Sort.Order> orderList;
        if(sort != null) {
            orderList = Lists.newArrayList(sort.iterator());
        } else {
            orderList = Lists.newArrayList();
        }
        if(sortPropterty != null) {
            orderList.add(new Sort.Order(direction != null ? direction : DEFAULT_DIRECTION, sortPropterty));
        }
        return CollectionUtils.isEmpty(orderList) ? null : Sort.by(orderList);
    }

    public static Sort create(Sort sort, String sortPropterty) {
        return create(sort, sortPropterty, DEFAULT_DIRECTION);
    }


    public static Sort create(Sort sort, Sort.Order... orders) {
        List<Sort.Order> orderList;
        if(sort != null) {
            orderList = Lists.newArrayList(sort.iterator());
        } else {
            orderList = Lists.newArrayList();
        }
        if(orders != null) {
            Collections.addAll(orderList, orders);
        }
        return CollectionUtils.isEmpty(orderList) ? null : Sort.by(orderList);
    }

    public static Sort create(Sort... sorts) {
        if(ArrayUtils.isEmpty(sorts)) {
            return null;
        }
        List<Sort.Order> orderList = Lists.newArrayList();
        for(Sort sort : sorts) {
            if(sort != null) {
                orderList.addAll(Lists.newArrayList(sort.iterator()));
            }
        }
        return Sort.by(orderList);
    }
}
