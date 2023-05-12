package com.test.sonicat.service;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestService {
    Yeasts createYeastsTest(Yeasts yeasts);
    Crystals createCrystalsTest(Crystals crystals);
    Page<Test> getAllTests(Pageable pageable);
}

