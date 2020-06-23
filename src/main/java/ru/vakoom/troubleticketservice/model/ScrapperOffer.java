package ru.vakoom.troubleticketservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

import static ru.vakoom.troubleticketservice.model.Type.TYPE_ID;

@Data
@Entity
@ToString(exclude = "ticket")
public class ScrapperOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private Double price;
    private Boolean inStore;
    @ManyToOne
    @JoinColumn(name = TYPE_ID, nullable = false)
    private Type type;
    private String shopName;
    @Column(length = 1023)
    private String link;
    private String age;
    @JsonIgnore
    @OneToOne(mappedBy = "scrapperOffer", cascade = CascadeType.ALL)
    private Ticket ticket;
}
