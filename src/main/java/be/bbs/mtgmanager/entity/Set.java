package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the Set database table.
 * 
 */
@Entity
@Table(name="Set")
@NamedQuery(name="Set.findAll", query="SELECT s FROM Set s")
public class Set implements Serializable {
	private static final long serialVersionUID = 1L;
	private int baseSetSize;
	private String block;
	private String boosterV3;
	private String code;
	private boolean isOnlineOnly;
	private String meta;
	private String mtgoCode;
	private String name;
	private String releaseDate;
	private int totalSetSize;
	private List<Card> cardsInSet;
	private List<Card> cardPrintings;
	private SetType setType;
	private List<Token> tokens;
	private int id;

	public Set() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBaseSetSize() {
		return this.baseSetSize;
	}

	public void setBaseSetSize(int baseSetSize) {
		this.baseSetSize = baseSetSize;
	}


	@Column(length=100)
	public String getBlock() {
		return this.block;
	}

	public void setBlock(String block) {
		this.block = block;
	}


	@Column(length=1024)
	public String getBoosterV3() {
		return this.boosterV3;
	}

	public void setBoosterV3(String boosterV3) {
		this.boosterV3 = boosterV3;
	}


	@Column(length=8)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public boolean getIsOnlineOnly() {
		return this.isOnlineOnly;
	}

	public void setIsOnlineOnly(boolean isOnlineOnly) {
		this.isOnlineOnly = isOnlineOnly;
	}


	@Column(length=512)
	public String getMeta() {
		return this.meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}


	@Column(length=8)
	public String getMtgoCode() {
		return this.mtgoCode;
	}

	public void setMtgoCode(String mtgoCode) {
		this.mtgoCode = mtgoCode;
	}


	@Column(length=100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Column(length=100)
	public String getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public int getTotalSetSize() {
		return this.totalSetSize;
	}

	public void setTotalSetSize(int totalSetSize) {
		this.totalSetSize = totalSetSize;
	}


	//bi-directional many-to-one association to Card
	@OneToMany(mappedBy="setBean", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCardsInSet() {
		return this.cardsInSet;
	}

	public void setCardsInSet(List<Card> cardsInSet) {
		this.cardsInSet = cardsInSet;
	}

	public Card addCardsInSet(Card cardsInSet) {
		getCardsInSet().add(cardsInSet);
		cardsInSet.setSetBean(this);

		return cardsInSet;
	}

	public Card removeCardsInSet(Card cardsInSet) {
		getCardsInSet().remove(cardsInSet);
		cardsInSet.setSetBean(null);

		return cardsInSet;
	}


	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="sets", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCardPrintings() {
		return this.cardPrintings;
	}

	public void setCardPrintings(List<Card> cardPrintings) {
		this.cardPrintings = cardPrintings;
	}


	//bi-directional many-to-one association to SetType
	@ManyToOne
	@JoinColumn(name="type", referencedColumnName="id")
	public SetType getSetType() {
		return this.setType;
	}

	public void setSetType(SetType setType) {
		this.setType = setType;
	}


	//bi-directional many-to-one association to Token
	@OneToMany(mappedBy="setBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setSetBean(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setSetBean(null);

		return token;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Set))
			return false;
		Set other = (Set) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Set [code=" + code + ", name=" + name + ", id=" + id + "]";
	}

}