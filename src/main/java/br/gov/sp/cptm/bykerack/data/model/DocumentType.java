package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "DOCUMENT_TYPE")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DOCUMENT_TYPE_ID")
    private Integer documentTypeId;

    @Column(name = "NAME", nullable = false)
    private String name;
}
