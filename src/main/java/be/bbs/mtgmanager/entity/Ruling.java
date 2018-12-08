package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Ruling database table.
 * 
 */
@Entity
@Table(name="Ruling")
@NamedQuery(name="Ruling.findAll", query="SELECT r FROM Ruling r")
public class Ruling implements Serializable {
	private static final long serialVersionUID = 1L;
	private String date;
	private int id;
	private String text;
	private Card cardBean;

	public Ruling() {
	}

	@Column(length=100)
	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Lob
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}


	//bi-directional many-to-one association to Card
	@ManyToOne
	@JoinColumn(name="card", referencedColumnName="id")
	public Card getCardBean() {
		return this.cardBean;
	}

	public void setCardBean(Card cardBean) {
		this.cardBean = cardBean;
	}

}