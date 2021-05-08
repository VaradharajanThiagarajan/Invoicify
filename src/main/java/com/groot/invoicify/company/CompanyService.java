package com.groot.invoicify.company;

import com.groot.invoicify.entity.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepos;

    public CompanyService(CompanyRepository companyRepos) {
        this.companyRepos = companyRepos;
    }

    public boolean create(CompanyDto companyDtoObject) {

        if (companyRepos.findByName(companyDtoObject.getName()) != null) {
            return false;
        } else {
            companyRepos.save(
                    new Company(companyDtoObject.getName(), companyDtoObject.getAddress(), companyDtoObject.getCity(),
                            companyDtoObject.getState(), companyDtoObject.getZip(), companyDtoObject.getContactName(),
                            companyDtoObject.getContactTitle(), companyDtoObject.getContactPhoneNumber()
                    )
            );
            return true;
        }
    }

    public List<CompanyDto> fetchAll() {

        return companyRepos.findAll()
                .stream()
                .map(companyEntity -> {
                    return new CompanyDto(
                            companyEntity.getName(),
                            companyEntity.getAddress(),
                            companyEntity.getCity(),
                            companyEntity.getState(),
                            companyEntity.getZip(),
                            companyEntity.getContactName(),
                            companyEntity.getContactTitle(),
                            companyEntity.getContactPhoneNumber()

                    );
                })
                .collect(Collectors.toList());
    }

    public CompanyDto findSingleCompany(String companyName) {

        Company companyFound = companyRepos.findByName(companyName);

        if (companyFound == null) {
            return null;
        }

        return new CompanyDto(companyFound.getName(), companyFound.getAddress(), companyFound.getCity(),
                companyFound.getState(), companyFound.getZip(), companyFound.getContactName(),
                companyFound.getContactTitle(), companyFound.getContactPhoneNumber());
    }
}