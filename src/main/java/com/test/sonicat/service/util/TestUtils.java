package com.test.sonicat.service.util;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import com.test.sonicat.repository.CrystalsRepository;
import com.test.sonicat.repository.YeastsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.test.sonicat.model.TestType.CRYSTALS;

@Slf4j
@Component
public class TestUtils {
    private final CrystalsRepository crystalsRepository;
    private final YeastsRepository yeastsRepository;

    public TestUtils(CrystalsRepository crystalsRepository, YeastsRepository yeastsRepository) {
        log.debug("TestUtils constructor called");
        this.yeastsRepository = yeastsRepository;
        this.crystalsRepository = crystalsRepository;
    }

    public String generateIdentifier(String prefix) {
        log.info("generateIdentifier called with prefix: {}", prefix);

        List<Test> tests = new ArrayList<>();

        if(Objects.equals(prefix, CRYSTALS.getPrefix())){
            List<Crystals> crystalTestsList = crystalsRepository.findAll();
            tests.addAll(crystalTestsList);
        } else {
            List<Yeasts> yeastsTestsList = yeastsRepository.findAll();
            tests.addAll(yeastsTestsList);
        }

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

    public List<Test> prepareTestList(Page<Crystals> crystalsPage, Page<Yeasts> yeastsPage) {
        List<Test> testList = new ArrayList<>();
        testList.addAll(crystalsPage.getContent());
        testList.addAll(yeastsPage.getContent());
        return testList;
    }
}

