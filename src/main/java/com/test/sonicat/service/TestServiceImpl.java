package com.test.sonicat.service;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import com.test.sonicat.repository.CrystalsRepository;
import com.test.sonicat.repository.TestRepository;
import com.test.sonicat.repository.YeastsRepository;
import com.test.sonicat.service.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
    private final YeastsRepository yeastsRepository;
    private final CrystalsRepository crystalsRepository;
    private final TestRepository testRepository;
    private final TestUtils testUtils;

    public TestServiceImpl(YeastsRepository yeastsRepository, CrystalsRepository crystalsRepository, TestRepository testRepository, TestUtils testUtils) {
        this.yeastsRepository = yeastsRepository;
        this.crystalsRepository = crystalsRepository;
        this.testRepository = testRepository;
        this.testUtils = testUtils;
    }

    @Override
    public Yeasts createYeastsTest(Yeasts yeasts) {
        log.info("createYeastsTest called with yeasts: {}", yeasts);
        yeasts.setIdentifier(testUtils.generateIdentifier("yeasts"));
        yeasts.setYeastsConcentration(((float) yeasts.getNumberYeasts() / 100) * 0.000091f);
        return testRepository.insert(yeasts);
    }

    @Override
    public Crystals createCrystalsTest(Crystals crystals) {
        log.info("createCrystalsTest called with crystals: {}", crystals);
        crystals.setIdentifier(testUtils.generateIdentifier("crystals"));
        return testRepository.insert(crystals);
    }

    @Override
    public Page<Test> getAllTests(Pageable pageable) {
        Page<Crystals> crystalsPage = crystalsRepository.findAll(pageable);
        Page<Yeasts> yeastsPage = yeastsRepository.findAll(pageable);

        List<Test> testList = new ArrayList<>();
        testList.addAll(crystalsPage.getContent());
        testList.addAll(yeastsPage.getContent());

        return new PageImpl<>(testList, pageable, crystalsPage.getTotalElements() + yeastsPage.getTotalElements());
    }

}

