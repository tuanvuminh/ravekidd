package com.ravekidd.v1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="USERS")
public class User {

    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(name = "USER_NAME", unique = true)
    private String username = null;

    @Column(name = "IMAGE")
    private String image = null;

    @Column(name = "PASSWORD")
    private String password = null;

    @OneToMany(mappedBy = "user")
    private List<Post> posts = null;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = null;

    public User() {
    }
}
