package com.github.rakhmedovrs.spring5webfluxrest.bootstrap;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Category;
import com.github.rakhmedovrs.spring5webfluxrest.domain.Vendor;
import com.github.rakhmedovrs.spring5webfluxrest.repository.CategoryRepository;
import com.github.rakhmedovrs.spring5webfluxrest.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author RakhmedovRS
 * @created 09-Sep-20
 */
@Component
@AllArgsConstructor
public class Bootstrap implements CommandLineRunner
{
	private final VendorRepository vendorRepository;
	private final CategoryRepository categoryRepository;

	@Override
	public void run(String... args)
	{
		if (categoryRepository.count().block() == 0)
		{
			loadCategories();
			System.out.println("Categories loaded -> " + categoryRepository.count().block());
		}

		if (vendorRepository.count().block() == 0)
		{
			loadVendors();
			System.out.println("Vendors loaded -> " + vendorRepository.count().block());
		}
	}

	private void loadCategories()
	{
		Category fruits = new Category();
		fruits.setDescription("Fruits");

		Category dried = new Category();
		dried.setDescription("Dried");

		Category fresh = new Category();
		fresh.setDescription("Fresh");

		Category exotic = new Category();
		exotic.setDescription("Exotic");

		Category nuts = new Category();
		nuts.setDescription("Nuts");

		categoryRepository.save(fruits).block();
		categoryRepository.save(dried).block();
		categoryRepository.save(fresh).block();
		categoryRepository.save(exotic).block();
		categoryRepository.save(nuts).block();
	}

	private void loadVendors()
	{
		Vendor vendor1 = new Vendor();
		vendor1.setFirstName("Vendor 1");
		vendorRepository.save(vendor1).block();

		Vendor vendor2 = new Vendor();
		vendor2.setFirstName("Vendor 2");
		vendorRepository.save(vendor2).block();
	}
}
