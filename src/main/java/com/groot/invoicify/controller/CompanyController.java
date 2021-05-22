package com.groot.invoicify.controller;

import com.groot.invoicify.dto.CompanyDto;
import com.groot.invoicify.service.CompanyService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * CompanyController
 */
@RestController
public class CompanyController {

	CompanyService companyService;

	/**
	 * @param companyService
	 */
	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	/**
	 * @return
	 */
	@GetMapping("company")
	public List<CompanyDto> getAllCompany() {
		return this.companyService.fetchAll();
	}

	/**
	 * @param companyName
	 * @return
	 */
	@GetMapping("company/{companyName}")
	public ResponseEntity<?> getAllCompany(@PathVariable String companyName) {
		CompanyDto companyDto = this.companyService.findSingleCompany(companyName);
		return new ResponseEntity<CompanyDto>(((companyDto != null) ? companyDto : null), HttpStatus.OK);
	}

	/**
	 * @param companyDtoObject
	 * @return
	 */
	@PostMapping("company")
	public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDtoObject) {

		Long companyId = this.companyService.create(companyDtoObject);

		if (companyId == null) {
			return new ResponseEntity<>("Duplicate Company Name.", HttpStatus.OK);
		}

		return new ResponseEntity<>("Successfully added new Company.Company id is " + companyId + ".", HttpStatus.CREATED);
	}

	/**
	 * @param id
	 * @param companyDtoObject
	 * @return
	 */
	@PatchMapping("company/{id}")
	public ResponseEntity<?> patchCompany(@PathVariable Long id, @RequestBody CompanyDto companyDtoObject) {
		return new ResponseEntity<>(((companyService.patchCompany(id, companyDtoObject)) ? "Update successful." : "No Company by given Id."), HttpStatus.OK);
	}
}
