package com.ncapas.labo_dos_n_capas.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "police_officers")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "police_station_id", nullable = false)
    private PoliceStation policeStation;

    @Column(name = "police_code", nullable = false, unique = true)
    private String code;

    @Column(name = "badge_number", nullable = false, unique = true)
    private String badgeNumber;
}