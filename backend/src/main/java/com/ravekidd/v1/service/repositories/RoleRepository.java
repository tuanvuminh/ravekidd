package com.ravekidd.v1.service.repositories;

import com.ravekidd.v1.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for accessing and managing Roles in the database.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
