package com.test.sonicat.service.util;

import com.test.sonicat.model.Test;
import com.test.sonicat.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TestUtils {

    private final TestRepository testRepository;

    public TestUtils(TestRepository testRepository) {
        log.debug("TestUtils constructor called");
        this.testRepository = testRepository;
    }

    public String generateIdentifier(String prefix) {
        log.info("generateIdentifier called with prefix: {}", prefix);
        List<Test> tests = testRepository.findByIdentifierStartingWith(prefix);
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
}

