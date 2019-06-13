package fr.saidus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.saidus.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
