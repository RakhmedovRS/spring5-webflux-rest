package com.github.rakhmedovrs.spring5webfluxrest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author RakhmedovRS
 * @created 07-Sep-20
 */
@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category
{
	@Id
	private String id;

	private String description;
}
