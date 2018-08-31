package com.jpa.service;


import com.jpa.entity.Girl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author Dragon-zg
 * @Date: 2017-12-16 19:37
 */
public interface GirlService {
    /**
     * 添加女生
     *
     * @param girl
     * @return
     */
    Girl addGirl(Girl girl) throws Exception;

    /**
     * 通过 id 删除女生
     *
     * @param id
     * @return
     */
    void deleteGirl(Integer id) throws Exception;


    /**
     * 根据 id 更新女生信息
     *
     * @param girl
     * @throws Exception
     */
    Girl updateGirl(Girl girl) throws Exception;

    /**
     * 通过 id 查找女生
     *
     * @param id
     * @return
     */
    Girl findById(Integer id) throws Exception;

    /**
     * 获取所有女生列表
     *
     * @return
     */
    List<Girl> getGirlList() throws Exception;

    /**
     * 获取所有女生分页列表
     */
    Page<Girl> getGirlPage(Pageable pageable) throws Exception;

    /**
     * 根据id获取女生年纪,当年纪小于20时,则抛出相关信息
     *
     * @param id
     * @return
     */
    Integer getAgeById(Integer id) throws Exception;
}
