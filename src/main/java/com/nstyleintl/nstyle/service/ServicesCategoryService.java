package com.nstyleintl.nstyle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.dto.ServicesCategoryDTO;
import com.nstyleintl.nstyle.model.ServicesCategory;
import com.nstyleintl.nstyle.repo.ServicesCategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicesCategoryService {
    private final ServicesCategoryRepository categoryRepository;

    public List<ServicesCategory> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Long createServicesCategory(ServicesCategoryDTO categoryDTO) {
        try {
            // TODO: save the image in the server and get the path
            ServicesCategory category = new ServicesCategory();
            category.setName(categoryDTO.getName());
            category.setCategoryImage(categoryDTO.getCategoryImage());
            category.setStatus(categoryDTO.getStatus());

            category = categoryRepository.save(category);

            return category.getCategoryId();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create service category", e);
        }
    }
}