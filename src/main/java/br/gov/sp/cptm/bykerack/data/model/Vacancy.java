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

    @Column(name = "DATE_ENTRY")
    private LocalDateTime dateEntry;

    @Setter
    @Column(name = "DATE_EXIT")
    private LocalDateTime dateExit;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID")
    private User employee;

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
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class VacancyId {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "VACANCY_ID")
        private Long id;

        @ManyToOne
        @JoinColumn(name = "BIKE_RACK_ID")
        private BikeRack bikeRack;

        @ManyToOne
        @JoinColumn(name = "USER_ID")
        private User user;

        public VacancyId(User user, BikeRack bikeRack) {
            this.user = user;
            this.bikeRack = bikeRack;
        }
    }
}
