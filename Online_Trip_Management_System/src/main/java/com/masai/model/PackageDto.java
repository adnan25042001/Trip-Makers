package com.masai.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {

	private Integer packageId;
	private String packageName;
	private String packageType;
	private String packageDescription;
	private Integer packageCost;

}
