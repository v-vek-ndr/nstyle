package com.nstyleintl.nstyle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicesSubCategoryDTO {
	
	private Long subCategoryId;
	private String name;
    private Long status;
    private Long categoryId;
    private String categoryName;
}
