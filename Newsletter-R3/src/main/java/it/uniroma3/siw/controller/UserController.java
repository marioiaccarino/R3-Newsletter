package it.uniroma3.siw.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.model.Articolo;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ArticoloRepository;
import it.uniroma3.siw.repository.CommentoRepository;
import it.uniroma3.siw.repository.CredentialsRepository;
import it.uniroma3.siw.service.UserService;

@Controller
public class UserController {

	@Autowired
	private ArticoloRepository articoloRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentoRepository commentoRepository;

	@Autowired
	private CredentialsRepository credentialsRepository;
		
	@GetMapping("/admin/utentiRegistrati")	
	public String utentiRegistrati(Model model)	{
		List<Credentials> lc = this.credentialsRepository.findByRole("DEFAULT");
		List<User> users = new ArrayList<>();
		for(Credentials c : lc)	{
			users.add(c.getUser());
		}
		model.addAttribute("users", users);
		return "admin/utentiRegistrati.html";
	}
	
	@GetMapping("/admin/articoli")
	public String adminArticoli(Model model)	{
		model.addAttribute("articoli", this.articoloRepository.findAll());
		return "admin/articoli.html";
	}
	
	@GetMapping("/admin/rimuoviUtente/{idu}")
	public String rimuoviUtente(@PathVariable("idu") Long idu, Model model)	{
		User u = this.userService.getUser(idu);

		Credentials cr = this.credentialsRepository.findByUser(u);
		this.credentialsRepository.delete(cr);
		List<Credentials> lc = this.credentialsRepository.findByRole("DEFAULT");
		List<User> users = new ArrayList<>();
		for(Credentials c : lc)	{
			users.add(c.getUser());
		}
		model.addAttribute("users", users);
		return "admin/utentiRegistrati.html";
		
	}
	
	@GetMapping("/admin/rimuoviArticoloDalSistema/{ida}")
	public String rimuoviArticoloDalSistema(@PathVariable("ida") Long ida, Model model)	{
		Articolo a = this.articoloRepository.findById(ida).get();
		for(Commento c : a.getCommenti())	{
			this.userService.getCurrentUser().getCommenti().remove(c);
			this.commentoRepository.delete(c);
		}
		this.userService.getCurrentUser().getArticoli().remove(a);
		this.articoloRepository.delete(a);
		model.addAttribute("articoli", this.articoloRepository.findAll());
		return "admin/articoli.html";
	}
	
}
