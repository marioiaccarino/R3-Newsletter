package it.uniroma3.siw.model.comparator;

import java.util.Comparator;

import it.uniroma3.siw.model.Articolo;

public class ComparatorePerTendenza implements Comparator<Articolo>{

	@Override
	public int compare(Articolo a1, Articolo a2) {
		return a1.getCommenti().size() - a2.getCommenti().size();
	}

}
