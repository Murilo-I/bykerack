package br.gov.sp.cptm.bykerack.data.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Entity(name = "USERS")
@Table(indexes = @Index(columnList = "document"))
public class User implements UserDetails {

    public User() {
        this.role = Role.USER;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long userId;

    @Setter
    @Column(name = "NAME", nullable = false)
    private String name;

    @Setter
    @ManyToOne
    @JoinColumn(name = "DOCUMENT_TYPE_ID")
    private DocumentType documentType;

    @Setter
    @Column(name = "DOCUMENT", nullable = false)
    private String document;

    @Setter
    @Column(name = "BIRTH_DATE", nullable = false)
    private LocalDate birthDate;

    @Setter
    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Setter
    @OneToOne
    @JoinColumn(name = "ACCESS_ID")
    private Access access;

    @Setter
    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(this.role);
    }

    @Override
    public String getPassword() {
        return this.access.getPassword();
    }

    @Override
    public String getUsername() {
        return this.access.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
