package br.gov.sp.cptm.bykerack.data.model;

import br.gov.sp.cptm.bykerack.web.dto.BicycleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity(name = "BICYCLE")
@NoArgsConstructor
public class Bicycle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BICYCLE_ID")
    private Long bicycleId;

    @Setter
    @Column(name = "MODEL", nullable = false)
    private String model;

    @Setter
    @Column(name = "COLOR", nullable = false)
    private String color;

    @Setter
    @Column(name = "CHASSIS", nullable = false)
    private String chassis;

    @Setter
    @Column(name = "BIKE_YEAR", nullable = false)
    private Integer year;

    @Setter
    @Column(name = "DETAILS")
    private String details;

    @Setter
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Bicycle(User user, BicycleDTO dto) {
        this.user = user;
        fromDto(dto);
    }

    public Bicycle fromDto(BicycleDTO dto) {
        this.model = dto.getModel();
        this.color = dto.getColor();
        this.chassis = dto.getChassis();
        this.year = dto.getYear();
        this.details = dto.getDetails();
        return this;
    }
}
