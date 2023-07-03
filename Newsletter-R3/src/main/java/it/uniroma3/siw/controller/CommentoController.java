package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.service.CommentoService;
import jakarta.validation.Valid;

@Controller
public class CommentoController {

	@Autowired
	private CommentoService commentoService;
	
	@GetMapping("/user/commentaArticolo/{ida}")
	public String commentaArticolo(@PathVariable("ida")Long ida, Model model) {
		this.commentoService.commentaArticolo(ida, model);
		return "user/formNewCommento.html";
	}
	
	@PostMapping("user/commento/{ida}")
	public String commento(@PathVariable("ida") Long ida, @Valid @ModelAttribute("commento") Commento commento,BindingResult bindingResult, Model model)	{
		
		this.commentoService.commentoEntry(ida, commento);
		
		this.commentoService.validate(commento, bindingResult);
		if(!bindingResult.hasErrors())	{
			this.commentoService.commentoOk(commento, model);
			return "user/commento.html";
		}
		this.commentoService.commentoFail(ida, model);
		return "user/formNewCommento.html";
	}
	
	@GetMapping("/user/guardaCommenti/{ida}")
	public String guardaCommenti(@PathVariable("ida") Long ida, Model model)	{
		this.commentoService.guardaCommenti(ida, model);
		return "user/commenti.html";
	}
}
