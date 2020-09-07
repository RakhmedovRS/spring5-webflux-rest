package com.github.rakhmedovrs.spring5webfluxrest.repository;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author RakhmedovRS
 * @created 07-Sep-20
 */
public interface CategoryRepository extends ReactiveMongoRepository<String, Category>
{
}
