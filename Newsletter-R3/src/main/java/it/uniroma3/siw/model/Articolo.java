package it.uniroma3.siw.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Cascade;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Articolo implements Comparable<Articolo>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String titolo;
	private String descrizione;
	private String urlImage;
	private LocalDateTime dataDiPubblicazione;
	
	public LocalDateTime getDataDiPubblicazione() {
		return dataDiPubblicazione;
	}

	public void setDataDiPubblicazione(LocalDateTime dataDiPubblicazione) {
		this.dataDiPubblicazione = dataDiPubblicazione;
	}

	@ManyToOne
	private User autore;
	
	@OneToMany(mappedBy = "articoloRecensito")
	@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	private List<Commento> commenti;

	public Articolo()	{
		this.commenti = new ArrayList<>();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(autore, descrizione, titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articolo other = (Articolo) obj;
		return Objects.equals(autore, other.autore) && Objects.equals(descrizione, other.descrizione)
				&& Objects.equals(titolo, other.titolo);
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public User getAutore() {
		return autore;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAutore(User autore) {
		this.autore = autore;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	@Override
	public int compareTo(Articolo o) {
		return this.getDataDiPubblicazione().compareTo(o.getDataDiPubblicazione());
	}
}
