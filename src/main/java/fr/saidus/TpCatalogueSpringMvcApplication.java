package fr.saidus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import fr.saidus.repository.ProduitRepository;

@SpringBootApplication
public class TpCatalogueSpringMvcApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TpCatalogueSpringMvcApplication.class, args);
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		
//		produitRepository.save(new Produit("MacBook", 444.44, 44));
//		produitRepository.save(new Produit("MacBook Air", 777.77, 77));
//		produitRepository.save(new Produit("MacBook Pro", 999.99, 99));
		
//		produitRepository.findAll().forEach(p->System.out.println(p.toString()));
	
	}

}
