package fr.saidus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.saidus.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

	@Query("SELECT p FROM Produit p WHERE p.designation LIKE :x ORDER BY p.idProduit")
	public Page<Produit> chercherParDesignation(@Param("x") String mc, Pageable pageable);
}