package com.test.sonicat.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class Crystals extends Test {
    @NonNull
    private int numberCrystals;
    @NonNull
    private float meanSize;
}
