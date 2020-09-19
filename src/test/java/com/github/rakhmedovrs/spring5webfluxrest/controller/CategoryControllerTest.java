package com.github.rakhmedovrs.spring5webfluxrest.controller;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Category;
import com.github.rakhmedovrs.spring5webfluxrest.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.*;

/**
 * @author RakhmedovRS
 * @created 11-Sep-20
 */
@ExtendWith(SpringExtension.class)
class CategoryControllerTest
{
	WebTestClient webTestClient;
	CategoryRepository categoryRepository;
	CategoryController categoryController;

	@BeforeEach
	void setUp()
	{
		categoryRepository = Mockito.mock(CategoryRepository.class);
		categoryController = new CategoryController(categoryRepository);
		webTestClient = WebTestClient.bindToController(categoryController).build();
	}

	@Test
	public void testList()
	{
		BDDMockito.given(categoryRepository.findAll())
			.willReturn(Flux.just(
				Category.builder().description("Category1").build(),
				Category.builder().description("Category1").build()));

		webTestClient
			.get()
			.uri("/api/v1/categories")
			.exchange()
			.expectBodyList(Category.class)
			.hasSize(2);
	}

	@Test
	public void testGetById()
	{
		BDDMockito.given(categoryRepository.findById(anyString()))
			.willReturn(Mono.just(Category.builder().description("Category1").build()));

		webTestClient
			.get()
			.uri("/api/v1/category/dfgdfg")
			.exchange()
			.expectBody(Category.class);
	}

	@Test
	public void testCreateCategory()
	{
		BDDMockito.given(categoryRepository.saveAll(any(Publisher.class)))
			.willReturn(Flux.just(Category.builder().description("Category1").build()));

		Mono<Category> categoryToSaveMono = Mono.just(Category.builder().description("Category1").build());

		webTestClient
			.post()
			.uri("/api/v1/categories")
			.body(categoryToSaveMono, Category.class)
			.exchange()
			.expectStatus()
			.isCreated();
	}

	@Test
	public void testUpdate()
	{
		BDDMockito.given(categoryRepository.save(any(Category.class)))
			.willReturn(Mono.just(Category.builder().description("Category1").build()));

		Mono<Category> categoryToUpdateMono = Mono.just(Category.builder().description("Category1").build());

		webTestClient
			.put()
			.uri("/api/v1/category/1")
			.body(categoryToUpdateMono, Category.class)
			.exchange()
			.expectBody(Category.class);
	}

	@Test
	public void testPatch()
	{
		BDDMockito.given(categoryRepository.findById(anyString()))
			.willReturn(Mono.just(Category.builder().build()));

		BDDMockito.given(categoryRepository.save(any(Category.class)))
			.willReturn(Mono.just(Category.builder().description("Category1").build()));

		Mono<Category> categoryToUpdateMono = Mono.just(Category.builder().description("Category1").build());

		webTestClient
			.patch()
			.uri("/api/v1/category/dfgdfgdfg")
			.body(categoryToUpdateMono, Category.class)
			.exchange()
			.expectBody(Category.class);

		BDDMockito.verify(categoryRepository).save(any());
	}
}