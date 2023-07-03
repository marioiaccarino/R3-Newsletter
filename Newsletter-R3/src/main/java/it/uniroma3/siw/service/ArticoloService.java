package it.uniroma3.siw.service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import it.uniroma3.siw.model.Articolo;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.comparator.ComparatorePerTendenza;
import it.uniroma3.siw.repository.ArticoloRepository;

@Service
public class ArticoloService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ArticoloRepository articoloRepository;
	
@Transactional
public void articoliScritti(Model model) {
	User u = this.userService.getCurrentUser();
	model.addAttribute("articoli", this.articoloRepository.findByAutore(u));
	return;
}
	
	@Transactional
	public void nuovoArticolo(Model model) {
		model.addAttribute("articolo", new Articolo());
		return ;
	}
	
	@Transactional
	public void newArticolo(Articolo articolo, Model model)  {
		User u = this.userService.getCurrentUser();
		articolo.setAutore(u);
		u.getArticoli().add(articolo);
		articolo.setDataDiPubblicazione(LocalDateTime.now());
		this.articoloRepository.save(articolo);
		model.addAttribute("articolo", articolo);
	}
	
	@Transactional	
	public void articoli(Model model)	{
		model.addAttribute("articoli", this.articoloRepository.findAll());
		return;
	}
	
	@Transactional
	public void ordinaArticoliPerTendenza(Model model) {
		List<Articolo> la = (List<Articolo>) this.articoloRepository.findAll();
		Collections.sort(la,new ComparatorePerTendenza());
		Collections.reverse(la);
		model.addAttribute("articoli", la);
		return;
	}
	
	@Transactional
	public void ordinaArticoliPerDataDiPubblicazione(Model model) {
		List<Articolo> la = (List<Articolo>) this.articoloRepository.findAll();
		Collections.sort(la);
		model.addAttribute("articoli", la);
		return;
	}
}
