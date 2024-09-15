package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity(name = "VACANCY")
public class Vacancy {

    @EmbeddedId
    private final VacancyId vacancyId;

    @Column(name = "DATE_ENTRY")
    private final LocalDateTime dateEntry;

    @Setter
    @Column(name = "DATE_EXIT")
    private LocalDateTime dateExit;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "EMPLOYEE")
    private final User employee;

    @Setter
    @Column(name = "EXIT_REASON")
    @Enumerated(EnumType.STRING)
    private ExitReason exitReason;

    public Vacancy(VacancyId vacancyId, User employee, LocalDateTime dateEntry) {
        this.vacancyId = vacancyId;
        this.employee = employee;
        this.dateEntry = dateEntry;
    }

    @Getter
    @Embeddable
    public static class VacancyId {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "VACANCY_ID")
        private Long id;

        @OneToOne
        @PrimaryKeyJoinColumn(name = "BIKE_RACK")
        private final BikeRack bikeRack;

        @OneToOne
        @PrimaryKeyJoinColumn(name = "USER")
        private final User user;

        public VacancyId(User user, BikeRack bikeRack) {
            this.user = user;
            this.bikeRack = bikeRack;
        }
    }
}
