package com.github.rakhmedovrs.spring5webfluxrest.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author RakhmedovRS
 * @created 09-Sep-20
 */
@Data
@Document
public class Vendor
{
	@Id
	private String id;

	private String firstName;
	private String lastName;
}
