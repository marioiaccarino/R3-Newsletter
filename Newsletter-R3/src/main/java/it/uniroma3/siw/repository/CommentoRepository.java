package it.uniroma3.siw.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.model.Articolo;
import it.uniroma3.siw.model.Commento;
import java.util.List;


public interface CommentoRepository extends CrudRepository<Commento, Long>{
	
	public List<Commento> findByArticoloRecensito(Articolo articoloRecensito);
}
