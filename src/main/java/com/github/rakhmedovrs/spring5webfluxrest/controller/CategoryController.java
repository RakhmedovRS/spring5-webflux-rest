package com.github.rakhmedovrs.spring5webfluxrest.controller;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Category;
import com.github.rakhmedovrs.spring5webfluxrest.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author RakhmedovRS
 * @created 10-Sep-20
 */
@RestController
@AllArgsConstructor
public class CategoryController
{
	private final CategoryRepository categoryRepository;

	@GetMapping("/api/v1/categories")
	public Flux<Category> list()
	{
		return categoryRepository.findAll();
	}

	@GetMapping("/api/v1/category/{id}")
	public Mono<Category> getById(@PathVariable String id)
	{
		return categoryRepository.findById(id);
	}

	@PostMapping("/api/v1/categories")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Void> post(@RequestBody Publisher<Category> categoryPublisher)
	{
		return categoryRepository.saveAll(categoryPublisher).then();
	}

	@PutMapping("/api/v1/category/{id}")
	public Mono<Category> update(@PathVariable String id, @RequestBody Category category)
	{
		category.setId(id);
		return categoryRepository.save(category);
	}

	@PatchMapping("/api/v1/category/{id}")
	public Mono<Category> patch(@PathVariable String id, @RequestBody Category category)
	{
		Category existingCategory = categoryRepository.findById(id).block();
		if (existingCategory.getDescription() == null || !existingCategory.getDescription().equals(category.getDescription()))
		{
			existingCategory.setDescription(category.getDescription());
			categoryRepository.save(existingCategory);
		}

		return Mono.just(existingCategory);
	}
}
