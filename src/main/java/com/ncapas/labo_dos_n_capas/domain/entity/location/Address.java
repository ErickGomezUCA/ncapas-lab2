package com.ncapas.labo_dos_n_capas.domain.entity.location;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "addresses")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "municipality_id", nullable = false)
    private Municipality municipality;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;
}
