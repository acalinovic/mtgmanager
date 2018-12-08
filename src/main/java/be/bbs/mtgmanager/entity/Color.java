package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the Color database table.
 * 
 */
@Entity
@Table(name="Color")
@NamedQuery(name="Color.findAll", query="SELECT c FROM Color c")
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;
	private double a;
	private int b;
	private String code;
	private int g;
	private String label;
	private int r;
	private List<Card> cards;
	private List<Token> tokens;
	private int id;

	public Color() {
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


	@Column(length=1)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public int getG() {
		return this.g;
	}

	public void setG(int g) {
		this.g = g;
	}


	@Column(length=100)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}


	public int getR() {
		return this.r;
	}

	public void setR(int r) {
		this.r = r;
	}


	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="colors", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}


	//bi-directional many-to-many association to Token
	@ManyToMany(mappedBy="colors", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

}