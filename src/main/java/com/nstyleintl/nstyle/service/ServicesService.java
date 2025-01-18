package com.nstyleintl.nstyle.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nstyleintl.nstyle.dto.ServicesDTO;
import com.nstyleintl.nstyle.exception.ResourceNotFoundException;
import com.nstyleintl.nstyle.model.Services;
import com.nstyleintl.nstyle.model.ServicesSubCategory;
import com.nstyleintl.nstyle.repo.ServicesRepository;
import com.nstyleintl.nstyle.repo.ServicesSubCategoryRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicesService {
	
	private ServicesSubCategoryRepository subCategoryRepository;
    private final ServicesRepository servicesRepository;

    public Services addService(ServicesDTO servicesDTO) {
        ServicesSubCategory subCategory = subCategoryRepository.findById(servicesDTO.getSubCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Subcategory not found for Id -> %d", servicesDTO.getSubCategoryId())));

        Services services = Services.builder()
	        .serviceName(servicesDTO.getServiceName())
	        .status(servicesDTO.getStatus())
	        .subcategory(subCategory).build();
        return servicesRepository.save(services);
    }
    
    public List<ServicesDTO> getAllServices() {
        return servicesRepository.findAll().stream()
        		.map(service -> mapToDTO(service)).toList();
    }
    
    public List<ServicesDTO> getServicesBySubCategoryId(Long subCategoryId) {
        return servicesRepository.findBySubcategory_SubCategoryId(subCategoryId).stream()
        		.map(service -> mapToDTO(service)).toList();
    }
    
    public ServicesDTO mapToDTO(Services services) {
		return ServicesDTO.builder().serviceId(services.getServiceId())
				.serviceName(services.getServiceName()).status(services.getStatus())
				.subCategoryId(services.getSubcategory().getSubCategoryId())
				.subCategoryName(services.getSubcategory().getName()).build();
	}
}
