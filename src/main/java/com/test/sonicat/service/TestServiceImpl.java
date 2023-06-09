package com.test.sonicat.service;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import com.test.sonicat.repository.CrystalsRepository;
import com.test.sonicat.repository.YeastsRepository;
import com.test.sonicat.service.util.TestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.test.sonicat.model.TestType.CRYSTALS;
import static com.test.sonicat.model.TestType.YEASTS;

@Slf4j
@Service
public class TestServiceImpl implements TestService {
    private final YeastsRepository yeastsRepository;
    private final CrystalsRepository crystalsRepository;
    private final TestUtils testUtils;

    public TestServiceImpl(YeastsRepository yeastsRepository, CrystalsRepository crystalsRepository, TestUtils testUtils) {
        log.debug("TestServiceImpl constructor called");
        this.yeastsRepository = yeastsRepository;
        this.crystalsRepository = crystalsRepository;
        this.testUtils = testUtils;
    }

    @Override
    public Yeasts createYeastsTest(Yeasts yeasts) {
        try {
            log.info("createYeastsTest called with yeasts: {}", yeasts);
            yeasts.setIdentifier(testUtils.generateIdentifier(YEASTS.getPrefix()));
            yeasts.setYeastsConcentration(((float) yeasts.getNumberYeasts() / 100) * 0.000091f);
            return yeastsRepository.insert(yeasts);
        } catch (Exception e) {
            log.error("Error creating yeasts test: {}", e.getMessage());
            throw new RuntimeException("Failed to create yeasts test");
        }
    }

    @Override
    public Crystals createCrystalsTest(Crystals crystals) {
        try {
            log.info("createCrystalsTest called with crystals: {}", crystals);
            crystals.setIdentifier(testUtils.generateIdentifier(CRYSTALS.getPrefix()));
            return crystalsRepository.insert(crystals);
        } catch (Exception e) {
            log.error("Error creating crystals test: {}", e.getMessage());
            throw new RuntimeException("Failed to create crystals test");
        }
    }

    @Override
    public Page<Test> getAllTests(Pageable pageable) {
        try {
            log.info("getAllTests called with pageable: {}", pageable);
            Page<Crystals> crystalsPage = crystalsRepository.findAll(pageable);
            Page<Yeasts> yeastsPage = yeastsRepository.findAll(pageable);

            List<Test> testList = testUtils.prepareTestList(crystalsPage, yeastsPage);

            log.info("getAllTests returning {} tests", testList.size());
            return new PageImpl<>(testList, pageable, crystalsPage.getTotalElements() + yeastsPage.getTotalElements());
        } catch (Exception e) {
            log.error("Error retrieving all tests: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve all tests");
        }
    }

}

