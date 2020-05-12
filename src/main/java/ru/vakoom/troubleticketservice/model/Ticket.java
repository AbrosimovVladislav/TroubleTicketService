package ru.vakoom.troubleticketservice.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "scrapper_offer_id", referencedColumnName = "id")
    ScrapperOffer scrapperOffer;
    String productIds;
    LocalDateTime createdTime;
    @Column(columnDefinition = "varchar(255) default 'NEW'", nullable = false)
    @Enumerated(EnumType.STRING)
    Status status;

    public enum Status {
        NEW, IN_PROGRESS, RESOLVED
    }
}
