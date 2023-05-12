package com.test.sonicat.repository;

import com.test.sonicat.model.Test;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
    @NonNull Page<Test> findAll(@NonNull Pageable pageable);
}
