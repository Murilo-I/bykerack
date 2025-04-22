package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity(name = "ADDRESS")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "ADDRESS", nullable = false)
    private String address;

    @Column(name = "NUMBER", nullable = false)
    private Integer number;

    @Column(name = "NEIGHBORHOOD", nullable = false)
    private String neighborhood;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "POSTAL_CODE", nullable = false)
    private String postalCode;

    @Column(name = "COMPLEMENT")
    private String complement;
}
