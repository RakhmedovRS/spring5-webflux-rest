package com.github.rakhmedovrs.spring5webfluxrest.controller;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Category;
import com.github.rakhmedovrs.spring5webfluxrest.domain.Vendor;
import com.github.rakhmedovrs.spring5webfluxrest.repository.VendorRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author RakhmedovRS
 * @created 11-Sep-20
 */
@ExtendWith(SpringExtension.class)
class VendorControllerTest
{
	WebTestClient webTestClient;
	VendorRepository vendorRepository;
	VendorController vendorController;

	@BeforeEach
	void setUp()
	{
		vendorRepository = Mockito.mock(VendorRepository.class);
		vendorController = new VendorController(vendorRepository);
		webTestClient = WebTestClient.bindToController(vendorController).build();
	}

	@Test
	public void testList()
	{
		BDDMockito.given(vendorRepository.findAll())
			.willReturn(Flux.just(
				Vendor.builder().firstName("firstName1").lastName("lastName1").build(),
				Vendor.builder().firstName("firstName2").lastName("lastName2").build()));

		webTestClient
			.get()
			.uri("/api/v1/vendors")
			.exchange()
			.expectBodyList(Vendor.class)
			.hasSize(2);
	}

	@Test
	public void testGetById()
	{
		BDDMockito.given(vendorRepository.findById(anyString()))
			.willReturn(Mono.just(Vendor.builder().firstName("firstName1").lastName("lastName1").build()));

		webTestClient
			.get()
			.uri("/api/v1/vendor/sdfsd")
			.exchange()
			.expectBody(Vendor.class);
	}

	@Test
	public void testPost()
	{
		BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
			.willReturn(Flux.just(Vendor.builder().firstName("firstName1").lastName("lastName1").build()));

		Flux<Vendor> vendorForPost = Flux.just(Vendor.builder().firstName("firstName1").lastName("lastName1").build());

		webTestClient
			.post()
			.uri("/api/v1/vendor")
			.body(vendorForPost, Vendor.class)
			.exchange()
			.expectBody(Vendor.class);
	}

	@Test
	public void TestPut()
	{
		BDDMockito.given(vendorRepository.save(any(Vendor.class)))
			.willReturn(Mono.just(Vendor.builder().firstName("firstName1").lastName("lastName1").build()));

		Mono<Category> vendorToUpdateMono = Mono.just(Category.builder().description("Category1").build());

		webTestClient
			.put()
			.uri("/api/v1/vendor/1")
			.body(vendorToUpdateMono, Vendor.class)
			.exchange()
			.expectBody(Vendor.class);
	}
}