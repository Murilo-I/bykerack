package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity(name = "VACANCY")
public class Vacancy {

    @EmbeddedId
    private VacancyId vacancyId;

    @Setter
    @Column(name = "EXIT_DATE")
    private LocalDateTime exitDate;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private User employee;

    @Setter
    @Column(name = "EXIT_REASON")
    @Enumerated(EnumType.STRING)
    private ExitReason exitReason;

    public Vacancy(VacancyId vacancyId, User employee) {
        this.vacancyId = vacancyId;
        this.employee = employee;
    }

    @Getter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class VacancyId {

        @Column(name = "ENTRY_DATE")
        private LocalDateTime entryDate;

        @ManyToOne
        @JoinColumn(name = "BIKE_RACK_ID")
        private BikeRack bikeRack;

        @ManyToOne
        @JoinColumn(name = "USER_ID")
        private User user;

        public VacancyId(LocalDateTime entryDate, User user, BikeRack bikeRack) {
            this.entryDate = entryDate;
            this.user = user;
            this.bikeRack = bikeRack;
        }
    }
}
