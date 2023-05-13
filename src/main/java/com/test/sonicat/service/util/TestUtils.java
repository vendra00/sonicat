package com.test.sonicat.service.util;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import com.test.sonicat.repository.CrystalsRepository;
import com.test.sonicat.repository.YeastsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.test.sonicat.model.TestType.CRYSTALS;
import static com.test.sonicat.model.TestType.YEASTS;

@Slf4j
@Component
public class TestUtils {
    private final CrystalsRepository crystalsRepository;
    private final YeastsRepository yeastsRepository;
    private static final DecimalFormat CONCENTRATION_FORMATTER = new DecimalFormat("#.###############");

    public TestUtils(CrystalsRepository crystalsRepository, YeastsRepository yeastsRepository) {
        log.debug("TestUtils constructor called");
        this.yeastsRepository = yeastsRepository;
        this.crystalsRepository = crystalsRepository;
    }

    public String generateIdentifier(String prefix) {
        log.info("generateIdentifier called with prefix: {}", prefix);

        List<Test> tests = new ArrayList<>();

        checkTestType(prefix, tests);

        return applyIdentifier(prefix, tests);
    }

    private static String applyIdentifier(String prefix, List<Test> tests) {
        if (!tests.isEmpty()) {
            log.debug("generateIdentifier found {} tests with prefix {}", tests.size(), prefix);
            String latestIdentifier = tests.get(tests.size() - 1).getIdentifier();
            int numericPart = Integer.parseInt(latestIdentifier.substring(prefix.length()));
            int newNumericPart = numericPart + 1;
            return prefix + String.format("%03d", newNumericPart);
        } else {
            log.debug("generateIdentifier found no tests with prefix {}", prefix);
            return prefix + "001";
        }
    }

    private void checkTestType(String prefix, List<Test> tests) {
        if(Objects.equals(prefix, CRYSTALS.getPrefix())){
            List<Crystals> crystalTestsList = crystalsRepository.findAll();
            tests.addAll(crystalTestsList);
        } else {
            List<Yeasts> yeastsTestsList = yeastsRepository.findAll();
            tests.addAll(yeastsTestsList);
        }
    }

    public List<Test> prepareTestList(Page<Crystals> crystalsPage, Page<Yeasts> yeastsPage) {
        List<Test> testList = new ArrayList<>();
        if (crystalsPage != null) {
            testList.addAll(crystalsPage.getContent());
        }
        if (yeastsPage != null) {
            testList.addAll(yeastsPage.getContent());
        }
        return testList;
    }

    public List<Test> fetchTestsByType(Pageable pageable, String testType) {
        Page<Crystals> crystalsPage = null;
        Page<Yeasts> yeastsPage = null;
        if (CRYSTALS.name().equalsIgnoreCase(testType)) {
            crystalsPage = crystalsRepository.findAll(pageable);
        } else if (YEASTS.name().equalsIgnoreCase(testType)) {
            yeastsPage = yeastsRepository.findAll(pageable);
        } else {
            crystalsPage = crystalsRepository.findAll(pageable);
            yeastsPage = yeastsRepository.findAll(pageable);
        }
        return prepareTestList(crystalsPage, yeastsPage);
    }

    public float applyYeastConcentrationFormula(Yeasts yeasts) {
        float concentration = ((float) yeasts.getNumberYeasts() / 100) * 0.000091f;
        return Float.parseFloat(CONCENTRATION_FORMATTER.format(concentration));
    }

}

