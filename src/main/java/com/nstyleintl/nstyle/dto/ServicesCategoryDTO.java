package com.nstyleintl.nstyle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicesCategoryDTO {

	private String name;
    private String categoryImage;
    private Long status;
}
