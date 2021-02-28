package com.lnnk.netty.service;

import com.lnnk.netty.entity.NettyTable;

/**
 * NettyTableService
 *
 * @author lnnk
 * @date 2019/6/25 15:59
 **/
public interface NettyTableService {

    /**
     * 保存信息
     */
    void save(NettyTable nettyTable);
}
