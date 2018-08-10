package com.jpa.respository;

import com.jpa.entity.Girl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Description JPA Dao 层
 * @Author Dragon-zg
 * @Date 2018/6/2 22:09
 * @Param
 * @return
 */
public interface GirlRespository extends JpaRepository<Girl, Integer>, PagingAndSortingRepository<Girl, Integer> , JpaSpecificationExecutor<Girl>{
}
