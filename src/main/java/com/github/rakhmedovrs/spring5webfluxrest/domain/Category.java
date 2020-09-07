package com.github.rakhmedovrs.spring5webfluxrest.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author RakhmedovRS
 * @created 07-Sep-20
 */
@Data
@Document
public class Category
{
	@Id
	private String id;

	private String description;
}
