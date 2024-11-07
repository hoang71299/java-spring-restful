package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {
  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  @PostMapping("/companies")
  public Company handleCreateCompany(Company company) {
    return this.companyRepository.save(company);
  }
}