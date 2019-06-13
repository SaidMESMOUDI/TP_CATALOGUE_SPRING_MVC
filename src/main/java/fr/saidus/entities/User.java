package fr.saidus.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "USERS", indexes = {@Index(columnList="LOGIN")}) 
public class User implements Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_USER")
	private Long idUser;
	
	@NotNull @Size(min = 3, max = 30) @Column(name = "NOM")
	private String nom;
	
	@NotNull @Size(min = 3, max = 50) @Column(name = "PRENOM")
	private String prenom;
	
	@NotNull @DateTimeFormat(pattern="yyyy-MM-dd") @Column(name = "DATE_NAISSANCE")
	private Date dateNaissance;
	
	@NotNull @Size(min = 5, max = 5) @Column(name = "SEXE")
	private String sexe;

	@NotNull @Size(min = 3, max = 100) @Column(name = "ADRESSE")
	private String adresse;
	
	@NotNull @Column(name = "CODE_POSTALE")
	private int codePostale;
	
	@NotNull @Email @Size(min = 6, max = 30) 
	@Column(name = "LOGIN") 
	private String login;
	
	@NotNull @Size(min = 8, max = 255) @Column(name = "PASS")
	private String password;
	
	@NotNull @Column(name = "ACTIVE")
	private int active;

	public User() {
		super();
	}
	
	public User(@NotNull @Size(min = 3, max = 30) String nom, @NotNull @Size(min = 3, max = 50) String prenom,
			@NotNull Date dateNaissance, @NotNull @Size(min = 5, max = 5) String sexe,
			@NotNull @Size(min = 3, max = 100) String adresse, @NotNull int codePostale,
			@NotNull @Email @Size(min = 6,max = 30) String login, @NotNull @Size(min = 8, max = 255) String password,
			@NotNull int active) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.sexe = sexe;
		this.adresse = adresse;
		this.codePostale = codePostale;
		this.login = login;
		this.password = password;
		this.active = active;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public int getCodePostale() {
		return codePostale;
	}

	public void setCodePostale(int codePostale) {
		this.codePostale = codePostale;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
	
}
