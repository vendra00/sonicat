package com.test.sonicat.repository;

import com.test.sonicat.model.Crystals;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrystalsRepository extends MongoRepository<Crystals, String> {
}
