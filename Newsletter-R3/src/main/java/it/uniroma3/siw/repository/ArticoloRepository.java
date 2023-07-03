package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Articolo;
import it.uniroma3.siw.model.User;

import java.util.List;


public interface ArticoloRepository extends CrudRepository<Articolo, Long>{
	public List<Articolo> findByAutore(User autore);
}
