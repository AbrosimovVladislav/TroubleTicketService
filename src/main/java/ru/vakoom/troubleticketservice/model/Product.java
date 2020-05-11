package ru.vakoom.troubleticketservice.model;

import lombok.Data;

import javax.persistence.*;

import static ru.vakoom.troubleticketservice.model.Type.TYPE_ID;

@Data
@Entity
public class Product {
    @Id
    private Long productId;
    private String model;
    private String brand;
    @ManyToOne
    @JoinColumn(name = TYPE_ID, nullable = false)
    private Type type;
    private String age;
}
