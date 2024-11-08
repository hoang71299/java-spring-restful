package vn.hoidanit.jobhunter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.domain.dto.Meta;
import vn.hoidanit.jobhunter.domain.dto.ResultPaginationDTO;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

@Service
public class CompanyService {
  private final CompanyRepository companyRepository;

  public CompanyService(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }

  public Company handleCreateCompany(Company company) {
    return this.companyRepository.save(company);
  }

  public ResultPaginationDTO handleGetCompany(Pageable pageable) {
    Page<Company> pCompany = this.companyRepository.findAll(pageable);
    ResultPaginationDTO rs = new ResultPaginationDTO();
    Meta mt = new Meta();

    mt.setPage(pCompany.getNumber() + 1);
    mt.setPageSize(pCompany.getSize());

    mt.setPages(pCompany.getTotalPages());
    mt.setTotal(pCompany.getTotalElements());

    rs.setMeta(mt);
    rs.setResult(pCompany.getContent());
    return rs;
  }

  public Company handleUpdateCompany(Company company) {
    Optional<Company> companyOptional = this.companyRepository.findById(company.getId());
    if (companyOptional.isPresent()) {
      Company currentCompany = companyOptional.get();
      currentCompany.setLogo(company.getLogo());
      currentCompany.setName(company.getName());
      currentCompany.setDescription(company.getDescription());
      currentCompany.setAddress(company.getAddress());
      return this.companyRepository.save(currentCompany);
    }
    return null;
  }

  public void handleDeleteCompany(long id) {
    this.companyRepository.deleteById(id);
  }
}