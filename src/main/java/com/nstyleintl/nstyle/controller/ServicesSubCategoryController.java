package com.nstyleintl.nstyle.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.ServicesSubCategoryDTO;
import com.nstyleintl.nstyle.service.ServicesSubCategoryService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/subcategories")
public class ServicesSubCategoryController {
    private final ServicesSubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<Long> createServicesSubCategory(@RequestBody ServicesSubCategoryDTO subCategoryDTO) throws Exception {
        Long subCategoryId = subCategoryService.createServicesSubCategory(subCategoryDTO);
        return ResponseEntity.ok(subCategoryId);
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllSubCategories() {
        List<ServicesSubCategoryDTO> subCategories = subCategoryService.getAllSubCategories();
        Map<String, Object> response = Map.of(
                "message", "",
                "result", true,
                "data", subCategories
        );
        return ResponseEntity.ok(response);
    }
}
