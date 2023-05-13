package com.test.sonicat.service;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import com.test.sonicat.repository.CrystalsRepository;
import com.test.sonicat.repository.YeastsRepository;
import com.test.sonicat.service.util.TestUtils;
import com.test.sonicat.service.validations.TestValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Yeasts createYeastsTest(Yeasts yeasts) {
        log.info("createYeastsTest called with yeasts: {}", yeasts);
        yeasts.setIdentifier(testUtils.generateIdentifier(YEASTS.getPrefix()));
        TestValidator.validateYeasts(yeasts);
        yeasts.setYeastsConcentration(testUtils.applyYeastConcentrationFormula(yeasts));
        return yeastsRepository.insert(yeasts);
    }

    @Override
    @Transactional
    public Crystals createCrystalsTest(Crystals crystals) {
        log.info("createCrystalsTest called with crystals: {}", crystals);
        crystals.setIdentifier(testUtils.generateIdentifier(CRYSTALS.getPrefix()));
        TestValidator.validateCrystals(crystals);
        return crystalsRepository.insert(crystals);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Test> getAllTests(Pageable pageable, String testType) {
        try {
            log.info("getAllTests called with pageable: {}, testType: {}", pageable, testType);
            List<Test> testList = testUtils.fetchTestsByType(pageable, testType);
            log.info("getAllTests returning {} tests", testList.size());
            return new PageImpl<>(testList, pageable, testList.size());
        } catch (Exception e) {
            log.error("Error retrieving all tests: {}", e.getMessage());
            throw new RuntimeException("Failed to retrieve all tests");
        }
    }

    @Override
    @Transactional
    public void deleteAllTests() {
        try {
            log.info("deleteAllTests called");
            yeastsRepository.deleteAll();
            crystalsRepository.deleteAll();
            log.info("All tests deleted");
        } catch (Exception e) {
            log.error("Error deleting all tests: {}", e.getMessage());
            throw new RuntimeException("Failed to delete all tests");
        }
    }
}

