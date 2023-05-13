package com.test.sonicat.service.validations;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

@Slf4j
public class TestValidator {
    private static final Pattern SPECIAL_CHARACTERS_PATTERN = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]");
    private static final int MAX_TEST_NAME_LENGTH = 30;

    public static void validateCrystals(Crystals crystals) {
        validateTest(crystals);
        if (crystals.getNumberCrystals() <= 0) {
            log.error("Error creating crystals test: Number of crystals cannot be zero or negative.");
            throw new ValidationException("Number of crystals cannot be zero or negative.");
        }
        if (crystals.getMeanSize() <= 0) {
            log.error("Error creating crystals test: Mean Size cannot be zero or negative.");
            throw new ValidationException("Mean Size cannot be zero or negative.");
        }
    }

    public static void validateYeasts(Yeasts yeasts) {
        validateTest(yeasts);
        if (yeasts.getSampleWeight() <= 0) {
            log.error("Error creating yeasts test: Sample Weight cannot be zero or negative.");
            throw new ValidationException("Sample Weight cannot be zero or negative.");
        }
        if (yeasts.getNumberYeasts() <= 0) {
            log.error("Error creating yeasts test: Number of Yeasts cannot be zero or negative.");
            throw new ValidationException("Number of Yeasts cannot be zero or negative.");
        }
    }

    public static void validateTest(Test test) {
        if (test.getName().isEmpty()) {
            log.error("Error creating test: Test name is required.");
            throw new ValidationException("Test name is required.");
        }
        if (test.getType().isEmpty()) {
            log.error("Error creating test: Test type is required.");
            throw new ValidationException("Test type is required.");
        }
        if (test.getName().length() > MAX_TEST_NAME_LENGTH) {
            log.error("Error creating test: Test name exceeds maximum length of {} characters.", MAX_TEST_NAME_LENGTH);
            throw new ValidationException("Test name exceeds maximum length of " + MAX_TEST_NAME_LENGTH + " characters.");
        }
        if (SPECIAL_CHARACTERS_PATTERN.matcher(test.getName()).find()) {
            log.error("Error creating test: Test name contains special characters.");
            throw new ValidationException("Test name cannot contain special characters.");
        }
    }
}
