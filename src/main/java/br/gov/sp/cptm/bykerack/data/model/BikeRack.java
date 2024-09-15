package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "BIKE_RACK")
public class BikeRack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BIKE_RACK_ID")
    private Integer bikeRackId;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "RAILWAY_LINE")
    private RailwayLine railwayLine;
 
    @Column(name = "VACANCIES")
    private Integer vacancies;

    @Column(name = "AVAILABLE_VACANCIES")
    private Integer availableVacancies;

    @Column(name = "STATION")
    private String station;
}
