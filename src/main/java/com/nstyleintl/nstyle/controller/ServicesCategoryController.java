package com.nstyleintl.nstyle.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.ServicesCategoryDTO;
import com.nstyleintl.nstyle.model.ServicesCategory;
import com.nstyleintl.nstyle.service.ServicesCategoryService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/categories")
public class ServicesCategoryController {
    private final ServicesCategoryService categoryService;

    @PostMapping
    public ResponseEntity<Long> createServicesCategory(@RequestBody ServicesCategoryDTO categoryDTO) {
        Long categoryId = categoryService.createServicesCategory(categoryDTO);
        return ResponseEntity.ok(categoryId);
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCategories() {
        List<ServicesCategory> categories = categoryService.getAllCategories();
        Map<String, Object> response = Map.of(
                "message", "",
                "result", true,
                "data", categories
        );
        return ResponseEntity.ok(response);
    }
}