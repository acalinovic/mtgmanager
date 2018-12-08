package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the Artist database table.
 * 
 */
@Entity
@Table(name="Artist")
@NamedQuery(name="Artist.findAll", query="SELECT a FROM Artist a")
public class Artist implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Card> cards;
	private List<Token> tokens;
	private int id;

	public Artist() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length=256)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Card
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(mappedBy="artistBean", fetch=FetchType.EAGER)
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Card addCard(Card card) {
		getCards().add(card);
		card.setArtistBean(this);

		return card;
	}

	public Card removeCard(Card card) {
		getCards().remove(card);
		card.setArtistBean(null);

		return card;
	}


	//bi-directional many-to-one association to Token
	@Fetch(value = FetchMode.SUBSELECT)
	@OneToMany(mappedBy="artistBean", fetch=FetchType.EAGER)
	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setArtistBean(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setArtistBean(null);

		return token;
	}

}