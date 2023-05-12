package com.test.sonicat.model;

import lombok.Getter;

import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class Test {
    @Id
    private String identifier;
    @NonNull
    private String name;
    @NonNull
    private String type;
}
