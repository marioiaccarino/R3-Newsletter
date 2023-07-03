package it.uniroma3.siw.controller.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Commento;
import it.uniroma3.siw.repository.CommentoRepository;
import it.uniroma3.siw.service.UserService;

@Component
public class CommentoValidator implements Validator {
	@Autowired
	private CommentoRepository commentoRepository;

	@Autowired
	private UserService userService;
	
	@Override
	public void validate(Object o, Errors errors) {
		Commento commento = (Commento)o;
		
		if(commento.getArticoloRecensito().getAutore().equals(this.userService.getCurrentUser()))	{
			errors.reject("commento.autore");
		}
		
		List<Commento> l =(List<Commento>)this.commentoRepository.findAll();

		for(Commento c : l)	{
				
				if(c.getCommentatore().equals(commento.getCommentatore()))	{
					if(c.getArticoloRecensito().equals(commento.getArticoloRecensito()))	{
						errors.reject("commento.giaScritto");

					}
				}
		}
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Commento.class.equals(aClass);
	}

}