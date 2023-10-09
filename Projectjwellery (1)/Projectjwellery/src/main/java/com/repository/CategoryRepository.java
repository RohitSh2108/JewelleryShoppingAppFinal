package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Cart;
import com.entity.Category;

@Repository
	public interface CategoryRepository extends JpaRepository<Category,Integer>{

	Optional<Category> findByCategoryName(String categoryName);

		/*
		 * Optional<Category> findByName(String categoryName);
		 */
	}


