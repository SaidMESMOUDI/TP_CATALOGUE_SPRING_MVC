package fr.saidus.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.saidus.entities.Produit;
import fr.saidus.entities.User;
import fr.saidus.entities.UserRole;
import fr.saidus.repository.ProduitRepository;
import fr.saidus.repository.UserRepository;
import fr.saidus.repository.UserRoleRepository;

@Controller
public class ProduitController {

	@Autowired
	private ProduitRepository produitRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private HttpServletRequest request;

	@RequestMapping(value = "/user/index", method = RequestMethod.GET)
	public String getPageProduits(Model model, @RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "size", defaultValue = "5") Integer size,
			@RequestParam(name = "motCle", defaultValue = "") String mc) {

		if (page != null && page != 0)
			page = page - 1;
		if (size != null && size != 0)
			size = size;

		Page<Produit> pageProduits = produitRepository.chercherParDesignation("%" + mc + "%",
				PageRequest.of(page, size));
		model.addAttribute("page", page);
		model.addAttribute("size", size);
		model.addAttribute("motCle", mc);
		model.addAttribute("pageListProduits", pageProduits);
		return "produits";// retourne la page produits.html
	}

	@RequestMapping(value = "/admin/add", method = RequestMethod.GET)
	public String addProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "saisieProduit";
	}

	@RequestMapping(value = "/admin/save", method = RequestMethod.POST)
	public String saveSaisieProduit(Model model, @Valid Produit produit, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "saisieProduit";

		produitRepository.save(produit);
		return "confirmation";
	}

	@RequestMapping(value = "/admin/edit", method = RequestMethod.GET)
	public String editProduit(Model model, @RequestParam(name = "id") Long id) { 
		Produit p = produitRepository.getOne(id);
		model.addAttribute("produit", p);
		return "editProduit";
	}

	@RequestMapping(value = "/admin/update", method = RequestMethod.POST)
	public String saveEditProduit(Model model, @Valid Produit produit, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "editProduit";

		produitRepository.save(produit);
		return "confirmation";
	}

	@RequestMapping(value = "/admin/delete", method = RequestMethod.GET)
	public String deleteProduit(Long id, int page, int size, String motCle) {
		produitRepository.deleteById(id);
		return "redirect:/user/index?page=" + (page + 1) + "&size=" + size + "&motCle=" + motCle;
	}
	
	@RequestMapping(value = "/admin/confirmation", method = RequestMethod.GET)
	public String confirmationSaveProduit(Long id, int page, int size, String motCle) {
		return "redirect:/user/index?page=" + (page + 1) + "&size=" + size + "&motCle=" + motCle;
	}
	
	@RequestMapping(value = "/")
	public String home() {
		return "redirect:/user/index";
	}
	
	@RequestMapping(value = "/403")
	public String accessDenied() {
		return "403";	
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";	
	}
	
	@RequestMapping(value = "/logout")
	public String logout() {
		return "redirect:/login?logout";	
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "inscription";	
	}
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(Model model, UserRole userRole, @Valid User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "inscription";
		}
		else if(user.getLogin().equals(request.getParameter("loginConfirm"))  &&  
				user.getPassword().equals(request.getParameter("passwordConfirm"))) {
			
			String password = user.getPassword();
			String hashedPassword = passwordEncoder.encode(password);
			user.setPassword(hashedPassword);
			userRepository.save(user);
			
			userRole.setLogin(user.getLogin());
			userRole.setRole("USER");
			userRoleRepository.save(userRole);
			
			return "login";
			
		}else {
			return "inscription";
		}
	
		
	}
	
}	