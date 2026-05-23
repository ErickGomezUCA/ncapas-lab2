package com.ncapas.labo_dos_n_capas.domain.entity;

import com.ncapas.labo_dos_n_capas.domain.entity.location.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "police_stations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PoliceStation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "station_name", nullable = false)
    private String stationName;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @OneToOne
    @JoinColumn(name = "director_id")
    private PoliceOfficer director;
}