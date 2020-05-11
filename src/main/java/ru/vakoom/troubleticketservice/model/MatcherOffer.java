package ru.vakoom.troubleticketservice.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MatcherOffer {
    private String name;
    private Long   productId;
    private String shop;
    private String brand;
    private String age;
    private Type type;
}
