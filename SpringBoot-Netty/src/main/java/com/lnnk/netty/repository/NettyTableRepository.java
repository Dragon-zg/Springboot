package com.lnnk.netty.repository;

import com.lnnk.netty.entity.NettyTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lnnk
 * @date 2019/6/25 14:09
 **/
@Repository
public interface NettyTableRepository extends JpaRepository<NettyTable, Long> {
}
