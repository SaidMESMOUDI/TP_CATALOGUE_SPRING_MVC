package fr.saidus.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.saidus.entities.Produit;
import fr.saidus.repository.ProduitRepository;

// Création du controleur RestFull pour le test de l'application front-end avec Angular
@RestController
@CrossOrigin(origins="http://localhost:4200", allowedHeaders="*")
public class ProduitRestController {

	@Autowired
	private ProduitRepository produitRepository;
	
	@RequestMapping(value = "/produits", method = RequestMethod.GET)
	public Page<Produit> showPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue="500") int size) {
		return produitRepository.findAll(PageRequest.of(page, size));
	}
	
	@GetMapping(value = "/produits/{id}")
    public Produit findProduit(@PathVariable(name = "id") Long id) {
        return produitRepository.findById(id).get(); // récupération du produit avec id
    }

    @PutMapping(value = "/produits/{id}")
    public Produit updateProduit(@PathVariable(name = "id") Long id, @RequestBody Produit p) {
        p.setIdProduit(id);
        return produitRepository.save(p); // modification du produit
    }

    @DeleteMapping(value = "/produits/{id}")
    public void deleteProduit(@PathVariable(name = "id") Long id) {
        produitRepository.deleteById(id); // suppression du produit
    }

    @PostMapping(value = "/produits")
    public Produit addProduit(@RequestBody Produit p) {
        return produitRepository.save(p); // ajout d'un produit
    }

}