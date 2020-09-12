package com.github.rakhmedovrs.spring5webfluxrest.controller;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Vendor;
import com.github.rakhmedovrs.spring5webfluxrest.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author RakhmedovRS
 * @created 12-Sep-20
 */
@RestController
@AllArgsConstructor
public class VendorController
{
	private final VendorRepository vendorRepository;

	@GetMapping("/api/v1/vendors")
	public Flux<Vendor> list()
	{
		return vendorRepository.findAll();
	}

	@GetMapping("/api/v1/vendor/{id}")
	public Mono<Vendor> getById(@PathVariable String id)
	{
		return vendorRepository.findById(id);
	}
}
