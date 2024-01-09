package com.ravekidd.v1.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "NAME")
    private String name = null;
}
