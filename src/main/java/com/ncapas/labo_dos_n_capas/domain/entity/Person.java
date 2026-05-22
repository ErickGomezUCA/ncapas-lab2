package com.ncapas.labo_dos_n_capas.domain.entity;

import com.ncapas.labo_dos_n_capas.domain.entity.location.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "persons")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "full_name", nullable = false)
    private String name;

    @Column(name = "dui", nullable = false, unique = true)
    private String dui;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;
}