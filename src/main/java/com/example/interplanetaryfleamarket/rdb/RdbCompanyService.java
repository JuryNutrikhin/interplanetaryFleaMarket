package com.example.interplanetaryfleamarket.rdb;

import com.example.interplanetaryfleamarket.entity.Company;
import com.example.interplanetaryfleamarket.rdb.repository.CompanyRepository;
import com.example.interplanetaryfleamarket.servise.CompanyService;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class RdbCompanyService implements CompanyService {

    // инъекция зависимостей через lombok
    private final CompanyRepository companyRepository;

    @Override
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(Integer id) {
        return companyRepository.findById(id);
    }

    @Override
    public Optional<Company> add(Company company) {
        return Optional.of(companyRepository.save(company));
    }

    @Override
    public Optional<Company> deleteById(Integer id) {
        Optional<Company> removable = findById(id);
        if(removable.isPresent()){
            companyRepository.deleteById(id);
        }
        return removable;
    }

    @Override
    public Optional<Company> updateById(Integer id, Company company) {
        Optional<Company> updated = findById(id);
        Optional<Company> duplicatedByNameCompany = companyRepository.findByCompanyName(company.getCompanyName());
        if(updated.isPresent() && (duplicatedByNameCompany.isEmpty()||
                Objects.equals(duplicatedByNameCompany.get().getId(),id) )){
                company.setId(id);
                return Optional.of(companyRepository.save(company));
        }else {
            return Optional.empty();
        }
    }
}
