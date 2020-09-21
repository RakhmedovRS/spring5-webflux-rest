package com.github.rakhmedovrs.spring5webfluxrest.controller;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Category;
import com.github.rakhmedovrs.spring5webfluxrest.domain.Vendor;
import com.github.rakhmedovrs.spring5webfluxrest.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
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

	@PostMapping("/api/v1/vendor/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Flux<Vendor> post(@RequestBody Publisher<Vendor> vendorPublisher)
	{
		return vendorRepository.saveAll(vendorPublisher);
	}

	@PutMapping("/api/v1/vendor/{id}")
	public Mono<Vendor> update(@PathVariable String id, @RequestBody Vendor vendor)
	{
		vendor.setId(id);
		return vendorRepository.save(vendor);
	}

	@PatchMapping("/api/v1/vendor/{id}")
	public Mono<Vendor> patch(@PathVariable String id, @RequestBody Vendor vendor)
	{
		Vendor existingVendor = vendorRepository.findById(id).block();
		if ((existingVendor.getFirstName() == null || !existingVendor.getFirstName().equals(vendor.getFirstName())
			|| (existingVendor.getLastName() == null || !existingVendor.getLastName().equals(vendor.getLastName()))))
		{
			existingVendor.setFirstName(vendor.getFirstName());
			existingVendor.setLastName(vendor.getLastName());
			vendorRepository.save(existingVendor);
		}

		return Mono.just(existingVendor);
	}
}
