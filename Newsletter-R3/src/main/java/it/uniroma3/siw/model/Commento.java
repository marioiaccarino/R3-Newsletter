package it.uniroma3.siw.model;

import java.util.Objects;

import org.hibernate.annotations.Cascade;import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Commento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String titolo;
	
	@NotBlank
	private String descrizione;
	
	@ManyToOne
	private User commentatore;
	
	@ManyToOne
	private Articolo articoloRecensito;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getCommentatore() {
		return commentatore;
	}

	public void setCommentatore(User commentatore) {
		this.commentatore = commentatore;
	}

	public Articolo getArticoloRecensito() {
		return articoloRecensito;
	}

	public void setArticoloRecensito(Articolo articoloRecensito) {
		this.articoloRecensito = articoloRecensito;
	}

	@Override
	public int hashCode() {
		return Objects.hash(articoloRecensito, descrizione, titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commento other = (Commento) obj;
		return Objects.equals(articoloRecensito, other.articoloRecensito)
				&& Objects.equals(descrizione, other.descrizione) && Objects.equals(titolo, other.titolo);
	}

	
}
