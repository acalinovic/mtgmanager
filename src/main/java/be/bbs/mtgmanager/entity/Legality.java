package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the Legality database table.
 * 
 */
@Entity
@Table(name="Legality")
@NamedQuery(name="Legality.findAll", query="SELECT l FROM Legality l")
public class Legality implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Card> cards;
	private int id;

	public Legality() {
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


	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="legalities", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

}