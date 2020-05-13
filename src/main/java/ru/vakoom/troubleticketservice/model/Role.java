package ru.vakoom.troubleticketservice.model;

import javax.persistence.*;

@Entity
public enum Role {
    ADMIN, OPERATOR;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(unique = true, nullable = false) private String name;
}
