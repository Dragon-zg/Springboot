package com.lnnk.netty.service.impl;

import com.lnnk.netty.entity.NettyTable;
import com.lnnk.netty.repository.NettyTableRepository;
import com.lnnk.netty.service.NettyTableService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Lnnk
 * @date 2021/2/28 16:47
 **/
@Service
public class NettyTableServiceImpl implements NettyTableService {

    private final NettyTableRepository nettyTableRepository;

    public NettyTableServiceImpl(NettyTableRepository nettyTableRepository) {
        this.nettyTableRepository = nettyTableRepository;
    }

    /**
     * 保存信息
     *
     * @param nettyTable
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(NettyTable nettyTable) {
        if (null != nettyTableRepository) {
            nettyTableRepository.save(nettyTable);
        }
    }
}
