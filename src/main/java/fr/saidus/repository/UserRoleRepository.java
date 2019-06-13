package fr.saidus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.saidus.entities.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long>{

}
