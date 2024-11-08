package vn.hoidanit.jobhunter.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.service.CompanyService;

@RestController
public class CompanyController {
  private final CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  }

  @PostMapping("/companies")
  public ResponseEntity<?> createCompany(@Valid @RequestBody Company reqCompany) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.companyService.handleCreateCompany(reqCompany));
  }

  @GetMapping("/companies")
  public ResponseEntity<ResultPaginationDTO> getCompany(
      @RequestParam("current") Optional<String> currentOptional,
      @RequestParam("pageSize") Optional<String> pageSizeOptional) {
    String sCurrent = currentOptional.isPresent() ? currentOptional.get() : "";
    String sPageSize = pageSizeOptional.isPresent() ? pageSizeOptional.get() : "";

    int current = Integer.parseInt(sCurrent);
    int pageSize = Integer.parseInt(sPageSize);

    Pageable pageable = PageRequest.of(current - 1, pageSize);
    return ResponseEntity.ok(this.companyService.handleGetCompany(pageable));
  }

  @PutMapping("/companies")
  public ResponseEntity<Company> updateCompany(@Valid @RequestBody Company reqCompany) {
    Company companies = this.companyService.handleUpdateCompany(reqCompany);
    return ResponseEntity.ok(companies);
  }

  @DeleteMapping("/companies/{id}")
  public ResponseEntity<Void> deleteCompany(@Valid @PathVariable("id") long id) {
    this.companyService.handleDeleteCompany(id);

    return ResponseEntity.ok(null);

  }
}
