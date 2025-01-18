package com.nstyleintl.nstyle.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicesDTO {

	private Long serviceId;
    private String serviceName;
    private int status;
    private Long subCategoryId;
    private String subCategoryName;
}
