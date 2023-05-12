package com.test.sonicat.service.util;

import com.test.sonicat.model.Test;
import com.test.sonicat.repository.TestRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestUtils {

    private final TestRepository testRepository;

    public TestUtils(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public String generateIdentifier(String prefix) {
        List<Test> tests = testRepository.findByIdentifierStartingWith(prefix);
        if (!tests.isEmpty()) {
            String latestIdentifier = tests.get(tests.size() - 1).getIdentifier();
            int numericPart = Integer.parseInt(latestIdentifier.substring(prefix.length()));
            int newNumericPart = numericPart + 1;
            return prefix + String.format("%03d", newNumericPart);
        } else {
            return prefix + "001";
        }
    }
}

