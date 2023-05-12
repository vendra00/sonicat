package com.test.sonicat.controller;

import com.test.sonicat.model.Crystals;
import com.test.sonicat.model.Test;
import com.test.sonicat.model.Yeasts;
import com.test.sonicat.service.TestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sonicat")
public class TestController {
    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/yeasts")
    public Yeasts createYeastsTest(@RequestBody Yeasts yeasts) {
        return testService.createYeastsTest(yeasts);
    }

    @PostMapping("/crystals")
    public Crystals createCrystalsTest(@RequestBody Crystals crystals) {
        return testService.createCrystalsTest(crystals);
    }
    @GetMapping("/tests")
    public Page<Test> getAllTests(Pageable pageable) {
        return testService.getAllTests(pageable);
    }
}



