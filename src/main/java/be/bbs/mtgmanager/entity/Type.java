package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;


/**
 * The persistent class for the Type database table.
 * 
 */
@Entity
@Table(name="Type")
@NamedQuery(name="Type.findAll", query="SELECT t FROM Type t")
public class Type implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Card> cardsOfType;
	private List<Card> cardsWithSubtype;
	private List<Card> cardsWithSuperType;
	private List<Card> cardsWithType;
	private int id;

	public Type() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length=100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Card
	@OneToMany(mappedBy="typeBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCardsOfType() {
		return this.cardsOfType;
	}

	public void setCardsOfType(List<Card> cardsOfType) {
		this.cardsOfType = cardsOfType;
	}

	public Card addCardsOfType(Card cardsOfType) {
		getCardsOfType().add(cardsOfType);
		cardsOfType.setTypeBean(this);

		return cardsOfType;
	}

	public Card removeCardsOfType(Card cardsOfType) {
		getCardsOfType().remove(cardsOfType);
		cardsOfType.setTypeBean(null);

		return cardsOfType;
	}


	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="subTypes", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCardsWithSubtype() {
		return this.cardsWithSubtype;
	}

	public void setCardsWithSubtype(List<Card> cardsWithSubtype) {
		this.cardsWithSubtype = cardsWithSubtype;
	}


	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="superTypes", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCardsWithSuperType() {
		return this.cardsWithSuperType;
	}

	public void setCardsWithSuperType(List<Card> cardsWithSuperType) {
		this.cardsWithSuperType = cardsWithSuperType;
	}


	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="types", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCardsWithType() {
		return this.cardsWithType;
	}

	public void setCardsWithType(List<Card> cardsWithType) {
		this.cardsWithType = cardsWithType;
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
		if (!(obj instanceof Type))
			return false;
		Type other = (Type) obj;
		return id == other.id && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Type [name=" + name + ", id=" + id + "]";
	}

}