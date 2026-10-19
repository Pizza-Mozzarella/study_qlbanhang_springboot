package com.springmasterclass.study.repository;

import com.springmasterclass.study.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
