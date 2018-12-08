package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import be.bbs.mtgmanager.entity.Card;

/**
 * The persistent class for the ForeignData database table.
 * 
 */
@Entity
@Table(name="ForeignData")
@NamedQuery(name="ForeignData.findAll", query="SELECT f FROM ForeignData f")
public class ForeignData implements Serializable {
	private static final long serialVersionUID = 1L;
	private String flavorText;
	private int id;
	private int multiverseId;
	private String name;
	private String text;
	private String type;
	private Card cardBean;
	private Language languageBean;

	public ForeignData() {
	}


	@Lob
	public String getFlavorText() {
		return this.flavorText;
	}

	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
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


	public int getMultiverseId() {
		return this.multiverseId;
	}

	public void setMultiverseId(int multiverseId) {
		this.multiverseId = multiverseId;
	}


	@Column(length=256)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Lob
	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}


	@Column(length=512)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
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


	//bi-directional many-to-one association to Language
	@ManyToOne
	@JoinColumn(name="language", referencedColumnName="id")
	public Language getLanguageBean() {
		return this.languageBean;
	}

	public void setLanguageBean(Language languageBean) {
		this.languageBean = languageBean;
	}

}