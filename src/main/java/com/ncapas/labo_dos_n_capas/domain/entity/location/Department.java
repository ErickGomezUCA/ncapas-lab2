package com.ncapas.labo_dos_n_capas.domain.entity.location;

import com.ncapas.labo_dos_n_capas.domain.entity.catalogue.Zone;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "departaments")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column (name = "department_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Zone zone;

}
