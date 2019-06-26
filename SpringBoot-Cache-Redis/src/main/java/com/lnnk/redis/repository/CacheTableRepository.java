package com.lnnk.redis.repository;

import com.lnnk.redis.entity.CacheTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lnnk
 * @date 2019/6/25 14:09
 **/
public interface CacheTableRepository extends JpaRepository<CacheTable, Long> {
}
