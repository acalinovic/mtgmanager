package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the FrameVersion database table.
 * 
 */
@Entity
@Table(name="FrameVersion")
@NamedQuery(name="FrameVersion.findAll", query="SELECT f FROM FrameVersion f")
public class FrameVersion implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<Card> cards;
	private int id;

	public FrameVersion() {
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
	@OneToMany(mappedBy="frameVersionBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCards() {
		return this.cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}

	public Card addCard(Card card) {
		getCards().add(card);
		card.setFrameVersionBean(this);

		return card;
	}

	public Card removeCard(Card card) {
		getCards().remove(card);
		card.setFrameVersionBean(null);

		return card;
	}

}