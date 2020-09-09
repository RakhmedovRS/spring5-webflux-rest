package com.github.rakhmedovrs.spring5webfluxrest.repository;

import com.github.rakhmedovrs.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author RakhmedovRS
 * @created 09-Sep-20
 */
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String>
{
}
