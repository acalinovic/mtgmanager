package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the BorderColor database table.
 * 
 */
@Entity
@Table(name="BorderColor")
@NamedQuery(name="BorderColor.findAll", query="SELECT b FROM BorderColor b")
public class BorderColor implements Serializable {
	private static final long serialVersionUID = 1L;
	private double a;
	private int b;
	private int g;
	private String name;
	private int r;
	private List<Card> cards;
	private List<Token> tokens;
	private int id;

	public BorderColor() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getA() {
		return this.a;
	}

	public void setA(double a) {
		this.a = a;
	}


	public int getB() {
		return this.b;
	}

	public void setB(int b) {
		this.b = b;
	}


	public int getG() {
		return this.g;
	}

	public void setG(int g) {
		this.g = g;
	}


	@Column(length=100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getR() {
		return this.r;
	}

	public void setR(int r) {
		this.r = r;
	}


	//bi-directional many-to-one association to Card
	@OneToMany(mappedBy="borderColorBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Card addCard(Card card) {
		getCards().add(card);
		card.setBorderColorBean(this);

		return card;
	}

	public Card removeCard(Card card) {
		getCards().remove(card);
		card.setBorderColorBean(null);

		return card;
	}


	//bi-directional many-to-one association to Token
	@OneToMany(mappedBy="borderColorBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setBorderColorBean(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setBorderColorBean(null);

		return token;
	}

}