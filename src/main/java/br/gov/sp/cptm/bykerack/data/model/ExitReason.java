package br.gov.sp.cptm.bykerack.data.model;

import lombok.Getter;

public enum ExitReason {

    BIKE_RETRIEVAL("User retrieve their bike"),
    BIKE_ABANDONMENT("Bike donated due to user abandonment");

    @Getter
    private final String description;

    ExitReason(String description) {
        this.description = description;
    }
}
