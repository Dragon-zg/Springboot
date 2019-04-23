package com.jpa.service.impl;

import com.jpa.entity.Girl;
import com.jpa.respository.GirlRespository;
import com.jpa.service.GirlService;
import com.web.enums.ExceptionCode;
import com.web.exception.CustomizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Dragon-zg
 * @Date: 2017-12-16 19:38
 */
@Service
public class GirlServiceImpl implements GirlService {

    @Autowired
    private GirlRespository girlRespository;

    /**
     * 添加女生
     *
     * @param girl
     * @return
     */
    @Override
    public Girl addGirl(Girl girl) throws Exception {
        return girlRespository.save(girl);
    }

    /**
     * 通过 id 删除女生
     *
     * @param id
     * @return
     */
    @Override
    public void deleteGirl(Integer id) throws Exception {
        Girl queryGirl = findById(id);
        if (null == queryGirl) {
            throw new CustomizedException(ExceptionCode.NOT_EXIST_GIRL);
        }else{
            girlRespository.delete(queryGirl);
        }
    }

    /**
     * 根据 id 更新女生信息
     *
     * @param girl
     * @throws Exception
     */
    @Override
    public Girl updateGirl(Girl girl) throws Exception {
        Girl queryGirl = findById(girl.getId());
        if (null == queryGirl) {
            throw new CustomizedException(ExceptionCode.NOT_EXIST_GIRL);
        }else{
           return girlRespository.save(girl);
        }
    }

    /**
     * 通过 id 查找女生
     *
     * @param id
     * @return
     */
    @Override
    public Girl findById(Integer id) throws Exception {
        return girlRespository.findById(id).orElse(null);
    }

    /**
     * 获取所有女生列表
     *
     * @return
     */
    @Override
    public List<Girl> getGirlList() throws Exception {
        return girlRespository.findAll();
    }

    @Override
    public Page<Girl> getGirlPage(Pageable pageable) throws Exception {
        return girlRespository.findAll(pageable);
    }

    /**
     * 根据id获取女生年纪,当年纪小于20时,则抛出相关信息
     *
     * @param id
     * @return
     */
    @Override
    public Integer getAgeById(Integer id) throws Exception {
        Girl queryGirl = findById(id);
        if (null == queryGirl) {
            throw new CustomizedException(ExceptionCode.NOT_EXIST_GIRL);
        }else if(queryGirl.getAge() < 20){
            throw new CustomizedException(ExceptionCode.SMALL_GIRL);
        }else{
            return queryGirl.getAge();
        }
    }
}
