package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "RAILWAY_LINE")
public class RailwayLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RAILWAY_LINE_ID")
    private Integer railwayLineId;

    @Column(name = "NAME")
    private String name;
}
