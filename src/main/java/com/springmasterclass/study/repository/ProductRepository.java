package com.springmasterclass.study.repository;

import com.springmasterclass.study.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // query select cố định số lượng lọc theo query đã viết. còn specicification thì có thể tăng giảm bộ lọc tùy ý

}
