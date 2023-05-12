package com.test.sonicat.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class Yeasts extends Test {
    @NonNull
    private int sampleWeight;
    @NonNull
    private int numberYeasts;
    @NonNull
    private float yeastsConcentration;
}
