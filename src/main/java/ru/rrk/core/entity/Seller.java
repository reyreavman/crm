package ru.rrk.core.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String contactInfo;

    @CreationTimestamp
    private LocalDateTime registrationDate;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Seller seller)) return false;

        if (getId() == null || seller.getId() == null) {
            return Objects.equals(getName(), seller.getName()) &&
                   Objects.equals(getContactInfo(), seller.getContactInfo()) &&
                   Objects.equals(getRegistrationDate(), seller.getRegistrationDate());
        }
        return getId().equals(seller.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getContactInfo(), getRegistrationDate());
    }
}