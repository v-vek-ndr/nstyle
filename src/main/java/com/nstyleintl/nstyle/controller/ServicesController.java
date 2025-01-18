package com.nstyleintl.nstyle.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nstyleintl.nstyle.dto.ServicesDTO;
import com.nstyleintl.nstyle.model.Services;
import com.nstyleintl.nstyle.service.ServicesService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/services")
public class ServicesController {
    private final ServicesService servicesService;
    
    @PostMapping
    public ResponseEntity<Map<String, Object>> addService(@RequestBody ServicesDTO servicesDTO) {
        Services createdService = servicesService.addService(servicesDTO);

        Map<String, Object> response = Map.of(
                "message", "Service added successfully",
                "result", true,
                "data", createdService
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllServices() {
        List<ServicesDTO> services = servicesService.getAllServices();
        Map<String, Object> response = Map.of(
                "message", "",
                "result", true,
                "data", services
        );
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/by-subcategory/{subcategoryId}")
    public ResponseEntity<Map<String, Object>> getServicesBySubCategoryId(@PathVariable Long subcategoryId) {
        List<ServicesDTO> services = servicesService.getServicesBySubCategoryId(subcategoryId);
        Map<String, Object> response = Map.of(
                "message", "",
                "result", true,
                "data", services
        );
        return ResponseEntity.ok(response);
    }
}
