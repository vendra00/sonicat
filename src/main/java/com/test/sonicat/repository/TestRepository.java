package com.test.sonicat.repository;

import com.test.sonicat.model.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
    // Custom query methods or overrides can be added here
    // if you need specific querying or data manipulation operations
    // for the Test entity.

    Page<Test> findAll(Pageable pageable);
}
