package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the Token database table.
 * 
 */
@Entity
@Table(name="Token")
@NamedQuery(name="Token.findAll", query="SELECT t FROM Token t")
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
	private int loyalty;
	private String name;
	private int number;
	private String power;
	private String side;
	private String text;
	private String toughness;
	private String type;
	private String uuid;
	private String watermark;
	private List<Card> cards;
	private List<Color> colors;
	private Artist artistBean;
	private BorderColor borderColorBean;
	private Set setBean;
	private int id;

	public Token() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLoyalty() {
		return this.loyalty;
	}

	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}


	@Column(length=100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	@Column(length=8)
	public String getPower() {
		return this.power;
	}

	public void setPower(String power) {
		this.power = power;
	}


	@Column(length=1)
	public String getSide() {
		return this.side;
	}

	public void setSide(String side) {
		this.side = side;
	}


	@Lob
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}


	@Column(length=8)
	public String getToughness() {
		return this.toughness;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}


	@Column(length=100)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Column(length=100)
	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}


	@Column(length=100)
	public String getWatermark() {
		return this.watermark;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}


	//bi-directional many-to-one association to Card
	@OneToMany(mappedBy="token", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Card addCard(Card card) {
		getCards().add(card);
		card.setToken(this);

		return card;
	}

	public Card removeCard(Card card) {
		getCards().remove(card);
		card.setToken(null);

		return card;
	}


	//bi-directional many-to-many association to Color
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
		name="Token_has_Color"
		, joinColumns={
			@JoinColumn(name="token_id", referencedColumnName="id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="color_id", referencedColumnName="id", nullable=false)
			}
		)
	public List<Color> getColors() {
		return this.colors;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}


	//bi-directional many-to-one association to Artist
	@ManyToOne
	@JoinColumn(name="artist", referencedColumnName="id")
	public Artist getArtistBean() {
		return this.artistBean;
	}

	public void setArtistBean(Artist artistBean) {
		this.artistBean = artistBean;
	}


	//bi-directional many-to-one association to BorderColor
	@ManyToOne
	@JoinColumn(name="borderColor", referencedColumnName="id")
	public BorderColor getBorderColorBean() {
		return this.borderColorBean;
	}

	public void setBorderColorBean(BorderColor borderColorBean) {
		this.borderColorBean = borderColorBean;
	}


	//bi-directional many-to-one association to Set
	@ManyToOne
	@JoinColumn(name="set", referencedColumnName="id")
	public Set getSetBean() {
		return this.setBean;
	}

	public void setSetBean(Set setBean) {
		this.setBean = setBean;
	}

}