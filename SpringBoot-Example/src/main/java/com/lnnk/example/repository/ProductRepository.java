package com.lnnk.example.repository;

import com.lnnk.example.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * ProductRepository
 *
 * @author lnnk
 * @date 2019/7/15 13:43
 */
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
}
