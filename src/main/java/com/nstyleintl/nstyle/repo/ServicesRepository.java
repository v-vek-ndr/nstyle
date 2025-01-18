package com.nstyleintl.nstyle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nstyleintl.nstyle.model.Services;

public interface ServicesRepository extends JpaRepository<Services, Long> {

	List<Services> findBySubcategory_SubCategoryId(Long subCategoryId);
}
