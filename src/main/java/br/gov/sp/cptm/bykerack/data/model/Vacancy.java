package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity(name = "VACANCY")
public class Vacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VACANCY_ID")
    private Long vacancyId;

    @Column(name = "DATE_ENTRY")
    private LocalDateTime dateEntry;

    @Column(name = "DATE_EXIT")
    private LocalDateTime dateExit;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "BIKE_RACK")
    private BikeRack bikeRack;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "USER")
    private User user;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "EMPLOYEE")
    private User employee;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "EXIT_REASON")
    private ExitReason exitReason;
}
