package com.nstyleintl.nstyle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.dto.ServicesSubCategoryDTO;
import com.nstyleintl.nstyle.exception.ResourceNotFoundException;
import com.nstyleintl.nstyle.model.ServicesCategory;
import com.nstyleintl.nstyle.model.ServicesSubCategory;
import com.nstyleintl.nstyle.repo.ServicesCategoryRepository;
import com.nstyleintl.nstyle.repo.ServicesSubCategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicesSubCategoryService {
	
	private ServicesCategoryRepository categoryRepository;
    private ServicesSubCategoryRepository subCategoryRepository;

    public List<ServicesSubCategoryDTO> getAllSubCategories() {
        return subCategoryRepository.findAll().stream()
        		.map(subCategory -> mapToDTO(subCategory)).toList();
    }
    
    public Long createServicesSubCategory(ServicesSubCategoryDTO subCategoryDTO) throws Exception {
    	ServicesCategory category = categoryRepository.findById(subCategoryDTO.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with ID: " + subCategoryDTO.getCategoryId()));

        ServicesSubCategory subCategory = new ServicesSubCategory();
        subCategory.setName(subCategoryDTO.getName());
        subCategory.setCategory(category);
        subCategory.setStatus(subCategoryDTO.getStatus());

        subCategory = subCategoryRepository.save(subCategory);

        return subCategory.getSubCategoryId();
    }
    
    public ServicesSubCategoryDTO mapToDTO(ServicesSubCategory subCategory) {
		return ServicesSubCategoryDTO.builder()
				.subCategoryId(subCategory.getSubCategoryId()).name(subCategory.getName()).status(subCategory.getStatus())
				.categoryId(subCategory.getCategory().getCategoryId())
				.categoryName(subCategory.getCategory().getName()).build();
	}
}