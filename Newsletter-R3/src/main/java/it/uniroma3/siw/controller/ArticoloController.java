package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Articolo;
import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.repository.ArticoloRepository;
import it.uniroma3.siw.service.ArticoloService;

@Controller
public class ArticoloController {
		
	@Autowired
	private ArticoloRepository articoloRepository;
	
	@Autowired
	private ArticoloService articoloService;
	
	@GetMapping("/user/articoliScritti")
	public String articoliScritti(Model model)	{
		this.articoloService.articoliScritti(model);
		return "user/articoliScritti.html";
	}
	
	@GetMapping("/user/nuovoArticolo")	
	public String nuovoArticolo(Model model) {
	    this.articoloService.nuovoArticolo(model);
		return "user/formNewArticolo.html";
	}
	
	@PostMapping("/user/articolo")
	public String newArticolo(@ModelAttribute("articolo") Articolo articolo, Model model)  {
		this.articoloService.newArticolo(articolo, model);
		return "user/articolo.html";
	}
	
	@GetMapping("/user/articoli")	
	public String articoli(Model model)	{
		this.articoloService.articoli(model);
		return "user/articoli.html";
	}
	
	@GetMapping("/user/ordinaArticoliPerTendenza")
	public String ordinaArticoliPerTendenza(Model model) {
		this.articoloService.ordinaArticoliPerTendenza(model);
		return "user/articoli.html";
	}
	
	@GetMapping("/user/ordinaArticoliPerDataDiPubblicazione")	
	public String ordinaArticoliPerDataDiPubblicazione(Model model) {
		this.articoloService.ordinaArticoliPerDataDiPubblicazione(model);
		return "user/articoli.html";
	}
}


