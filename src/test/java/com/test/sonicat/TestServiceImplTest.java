package com.test.sonicat;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Yeasts;
import com.test.sonicat.repository.CrystalsRepository;
import com.test.sonicat.repository.YeastsRepository;
import com.test.sonicat.service.TestServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestServiceImplTest {

    @Mock
    private YeastsRepository yeastsRepository;

    @Mock
    private CrystalsRepository crystalsRepository;

    @InjectMocks
    private TestServiceImpl testService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createYeastsTest_Success() {
        // Prepare test data
        Yeasts yeasts = new Yeasts();
        yeasts.setName("Yeasts Test");
        yeasts.setType("yeasts");
        yeasts.setIdentifier("YEASTS001");
        yeasts.setSampleWeight(100);
        yeasts.setNumberYeasts(500);

        // Mock the behavior of the repository
        when(yeastsRepository.insert(any(Yeasts.class))).thenReturn(yeasts);

        // Perform the test
        Yeasts result = testService.createYeastsTest(yeasts);

        // Verify the repository method was called with the correct argument
        verify(yeastsRepository).insert(eq(yeasts));

        // Assert the returned result matches the expected values
        assertEquals("Yeasts Test", result.getName());
        assertEquals("yeasts", result.getType());
        assertEquals("YEASTS001", result.getIdentifier());
        assertEquals(500, result.getNumberYeasts());
        assertEquals(0.000455, result.getYeastsConcentration(), 0.0001);
    }


    @Test
    public void createCrystalsTest_Success() {
        // Prepare test data
        Crystals crystals = new Crystals();
        crystals.setName("Crystals Test");
        crystals.setType("crystals");
        crystals.setIdentifier("CRYSTALS001");
        crystals.setNumberCrystals(10);
        crystals.setMeanSize(5.5f);

        // Mock the behavior of the repository
        when(crystalsRepository.insert(any(Crystals.class))).thenReturn(crystals);

        // Perform the test
        Crystals result = testService.createCrystalsTest(crystals);

        // Verify the repository method was called with the correct argument
        verify(crystalsRepository).insert(eq(crystals));

        // Assert the returned result matches the expected values
        assertEquals("Crystals Test", result.getName());
        assertEquals("crystals", result.getType());
        assertEquals("CRYSTALS001", result.getIdentifier());
        assertEquals(10, result.getNumberCrystals());
        assertEquals(5.5f, result.getMeanSize(), 0.0001f);
    }
}
