package guru.springframework.repositories;

import java.util.List;

import guru.springframework.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import guru.springframework.domain.Permission;

public interface PermissionRepo extends JpaRepository<Permission,Long> {

    List<Permission> findByRoles_id(Long id);
    List<Permission> findAllByRoles(Role role);
    Permission findByNom(String nom);
}
