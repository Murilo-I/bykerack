package br.gov.sp.cptm.bykerack.data.model;

import lombok.Getter;

public enum Station {

    STATION("");

    @Getter
    private final String name;

    Station(String name) {
        this.name = name;
    }
}
