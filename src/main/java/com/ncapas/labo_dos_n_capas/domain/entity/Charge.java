package com.ncapas.labo_dos_n_capas.domain.entity;

import com.ncapas.labo_dos_n_capas.domain.entity.catalogue.ChargeType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "charges")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Charge {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "charge_date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "accuser_id", nullable = false)
    private Person accuser;

    @ManyToOne
    @JoinColumn(name = "accused_id", nullable = false)
    private Person accused;

    @Enumerated(EnumType.STRING)
    @Column(name = "charge_type", nullable = false)
    private ChargeType chargeType;

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @ManyToOne
    @JoinColumn(name = "police_officer_id", nullable = false)
    private PoliceOfficer policeOfficer;
}