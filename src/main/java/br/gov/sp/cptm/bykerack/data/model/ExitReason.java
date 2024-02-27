package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "EXIT_REASON")
public class ExitReason {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EXIT_REASON_ID")
    private Integer exitReasonId;

    @Column(name = "DESCRIPTION")
    private String description;
}
