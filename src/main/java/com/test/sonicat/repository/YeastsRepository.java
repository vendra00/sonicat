package com.test.sonicat.repository;

import com.test.sonicat.model.Yeasts;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YeastsRepository extends MongoRepository<Yeasts, String> {
}
