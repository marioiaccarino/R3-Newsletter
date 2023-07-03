package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import it.uniroma3.siw.repository.ArticoloRepository;
import it.uniroma3.siw.repository.CommentoRepository;

import it.uniroma3.siw.controller.validator.CommentoValidator;
import it.uniroma3.siw.model.*;


@Service
public class CommentoService {
	
	@Autowired
	protected CommentoRepository commentoRepository;
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected ArticoloRepository articoloRepository;
	
	@Autowired
	protected CommentoValidator commentoValidator;

	@Transactional
	public void commentaArticolo(Long ida, Model model) {
		model.addAttribute("articolo",this.articoloRepository.findById(ida).get());
		model.addAttribute("commento", new Commento());
		return;
	}
	
	@Transactional
	public void commentoEntry(Long ida, Commento commento) {
		commento.setArticoloRecensito(this.articoloRepository.findById(ida).get());
		commento.setCommentatore(this.userService.getCurrentUser());
	}
	
	@Transactional
	public void validate(Commento commento, BindingResult bindingResult)	{
		this.commentoValidator.validate(commento, bindingResult);
		return;
	}
	
	@Transactional
	public void commentoOk(Commento commento, Model model) {
		this.commentoRepository.save(commento);
		model.addAttribute("commento", commento);
		return;
	}
	
	@Transactional
	public void commentoFail(Long ida, Model model)	{
		model.addAttribute("articolo", this.articoloRepository.findById(ida).get());
		return;
}
	
	@Transactional
	public void guardaCommenti(Long ida, Model model) {
		Articolo a = this.articoloRepository.findById(ida).get();
		model.addAttribute("commenti", this.commentoRepository.findByArticoloRecensito(a));
		model.addAttribute("articolo", a);
	}
}
