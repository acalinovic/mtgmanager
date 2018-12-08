package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.Objects;
import be.bbs.mtgmanager.entity.*;


/**
 * The persistent class for the Card database table.
 * 
 */
@Entity
@Table(name="Card")
@NamedQuery(name="Card.findAll", query="SELECT c FROM Card c")
public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	private Artist artistBean;
	private BorderColor borderColorBean;
	private List<Card> cardVariations;
	private List<Color> colors;
	private double convertedManaCost;
	private double faceConvertedManaCost;
	private String flavorText;
	private List<ForeignData> foreignDatas;
	private FrameVersion frameVersionBean;
	private boolean hasFoil;
	private boolean hasNonFoil;
	private int id;
	private boolean isFoilOnly;
	private boolean isOnlineOnly;
	private boolean isOversized;
	private boolean isReserved;
	private boolean isTimeshifted;
	private Layout layoutBean;
	private List<Legality> legalities;
	private int loyalty;
	private String manaCost;
	private int multiverseId;
	private String name;
	private String names;
	private int number;
	private String originalText;
	private String power;
	private Rarity rarityBean;
	private List<Ruling> rulings;
	private Set setBean;
	private List<Set> sets;
	private String text;
	private Token token;
	private String toughness;
	private String type;
	private Type typeBean;
	private List<Type> subTypes;
	private List<Type> superTypes;
	private List<Type> types;
	private String uuid;
	private String watermark;
	
	public Card() {
	}

	public ForeignData addForeignData(ForeignData foreignData) {
		getForeignDatas().add(foreignData);
		foreignData.setCardBean(this);

		return foreignData;
	}

	public Ruling addRuling(Ruling ruling) {
		getRulings().add(ruling);
		ruling.setCardBean(this);

		return ruling;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Card))
			return false;
		Card other = (Card) obj;
		return id == other.id && multiverseId == other.multiverseId && Objects.equals(name, other.name)
				&& Objects.equals(sets, other.sets);
	}

	//bi-directional many-to-one association to Artist
	@ManyToOne
	@JoinColumn(name="artist", referencedColumnName="id")
	public Artist getArtistBean() {
		return this.artistBean;
	}

	//bi-directional many-to-one association to BorderColor
	@ManyToOne
	@JoinColumn(name="borderColor", referencedColumnName="id")
	public BorderColor getBorderColorBean() {
		return this.borderColorBean;
	}



	//bi-directional many-to-many association to Color
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
		name="Card_has_Color"
		, joinColumns={
			@JoinColumn(name="card_id", referencedColumnName="id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="color_id", referencedColumnName="id", nullable=false)
			}
		)
	public List<Color> getColors() {
		return this.colors;
	}


	public double getConvertedManaCost() {
		return this.convertedManaCost;
	}

	public double getFaceConvertedManaCost() {
		return this.faceConvertedManaCost;
	}


	@Lob
	public String getFlavorText() {
		return this.flavorText;
	}

	
	//bi-directional many-to-many association to Card
	@ManyToMany(mappedBy="uuid", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Card> getCardVariations() {
		return this.cardVariations;
	}

	public void setCardVariations(List<Card> cardVariations) {
		this.cardVariations = cardVariations;
	}


	//bi-directional many-to-one association to ForeignData
	@OneToMany(mappedBy="cardBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<ForeignData> getForeignDatas() {
		return this.foreignDatas;
	}


	//bi-directional many-to-one association to FrameVersion
	@ManyToOne
	@JoinColumn(name="frameVersion", referencedColumnName="id")
	public FrameVersion getFrameVersionBean() {
		return this.frameVersionBean;
	}

	public boolean getHasFoil() {
		return this.hasFoil;
	}


	public boolean getHasNonFoil() {
		return this.hasNonFoil;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsFoilOnly() {
		return this.isFoilOnly;
	}

	public boolean getIsOnlineOnly() {
		return this.isOnlineOnly;
	}


	public boolean getIsOversized() {
		return this.isOversized;
	}

	public boolean getIsReserved() {
		return this.isReserved;
	}


	public boolean getIsTimeshifted() {
		return this.isTimeshifted;
	}

	//bi-directional many-to-one association to Layout
	@ManyToOne
	@JoinColumn(name="layout", referencedColumnName="id")
	public Layout getLayoutBean() {
		return this.layoutBean;
	}


	//bi-directional many-to-many association to Legality
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
		name="Card_has_Legality"
		, joinColumns={
			@JoinColumn(name="card_id", referencedColumnName="id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="Legality_id", referencedColumnName="id", nullable=false)
			}
		)
	public List<Legality> getLegalities() {
		return this.legalities;
	}

	public int getLoyalty() {
		return this.loyalty;
	}


	@Column(length=100)
	public String getManaCost() {
		return this.manaCost;
	}

	public int getMultiverseId() {
		return this.multiverseId;
	}


	@Column(length=256)
	public String getName() {
		return this.name;
	}

	@Column(length=1024)
	public String getNames() {
		return this.names;
	}


	public int getNumber() {
		return this.number;
	}

	@Lob
	public String getOriginalText() {
		return this.originalText;
	}


	@Column(length=8)
	public String getPower() {
		return this.power;
	}

	//bi-directional many-to-one association to Rarity
	@ManyToOne
	@JoinColumn(name="rarity", referencedColumnName="id")
	public Rarity getRarityBean() {
		return this.rarityBean;
	}


	//bi-directional many-to-one association to Ruling
	@OneToMany(mappedBy="cardBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Ruling> getRulings() {
		return this.rulings;
	}

	//bi-directional many-to-one association to Set
	@ManyToOne
	@JoinColumn(name="set", referencedColumnName="id")
	public Set getSetBean() {
		return this.setBean;
	}


	//bi-directional many-to-many association to Set
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
		name="Card_has_Printing"
		, joinColumns={
			@JoinColumn(name="card_id", referencedColumnName="id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="set_id", referencedColumnName="id", nullable=false)
			}
		)
	public List<Set> getSets() {
		return this.sets;
	}

	@Lob
	public String getText() {
		return this.text;
	}


	//bi-directional many-to-one association to Token
	@ManyToOne
	@JoinColumn(name="tokenReference", referencedColumnName="id")
	public Token getToken() {
		return this.token;
	}

	@Column(length=8)
	public String getToughness() {
		return this.toughness;
	}


	@Column(length=512)
	public String getType() {
		return this.type;
	}

	//bi-directional many-to-one association to Type
	@ManyToOne
	@JoinColumn(name="originalType", referencedColumnName="id")
	public Type getTypeBean() {
		return this.typeBean;
	}


	//bi-directional many-to-many association to Type
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
		name="Card_has_Subtype"
		, joinColumns={
			@JoinColumn(name="card_id", referencedColumnName="id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="type_id", referencedColumnName="id", nullable=false)
			}
		)
	public List<Type> getSubTypes() {
		return this.subTypes;
	}

	//bi-directional many-to-many association to Type
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
		name="Card_has_Supertype"
		, joinColumns={
			@JoinColumn(name="card_id", referencedColumnName="id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="type_id", referencedColumnName="id", nullable=false)
			}
		)
	public List<Type> getSuperTypes() {
		return this.superTypes;
	}


	//bi-directional many-to-many association to Type
	@ManyToMany(fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
		name="Card_has_Type"
		, joinColumns={
			@JoinColumn(name="card_id", referencedColumnName="id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="type_id", referencedColumnName="id", nullable=false)
			}
		)
	public List<Type> getTypes() {
		return this.types;
	}

	public String getUuid() {
		return uuid;
	}


	@Column(length=100)
	public String getWatermark() {
		return this.watermark;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, multiverseId, name, sets);
	}


	public ForeignData removeForeignData(ForeignData foreignData) {
		getForeignDatas().remove(foreignData);
		foreignData.setCardBean(null);

		return foreignData;
	}

	public Ruling removeRuling(Ruling ruling) {
		getRulings().remove(ruling);
		ruling.setCardBean(null);

		return ruling;
	}


	public void setArtistBean(Artist artistBean) {
		this.artistBean = artistBean;
	}

	public void setBorderColorBean(BorderColor borderColorBean) {
		this.borderColorBean = borderColorBean;
	}

	public void setColors(List<Color> colors) {
		this.colors = colors;
	}


	public void setConvertedManaCost(double convertedManaCost) {
		this.convertedManaCost = convertedManaCost;
	}

	public void setFaceConvertedManaCost(double faceConvertedManaCost) {
		this.faceConvertedManaCost = faceConvertedManaCost;
	}


	public void setFlavorText(String flavorText) {
		this.flavorText = flavorText;
	}

	public void setForeignDatas(List<ForeignData> foreignDatas) {
		this.foreignDatas = foreignDatas;
	}


	public void setFrameVersionBean(FrameVersion frameVersionBean) {
		this.frameVersionBean = frameVersionBean;
	}

	public void setHasFoil(boolean hasFoil) {
		this.hasFoil = hasFoil;
	}


	public void setHasNonFoil(boolean hasNonFoil) {
		this.hasNonFoil = hasNonFoil;
	}

	public void setIsFoilOnly(boolean isFoilOnly) {
		this.isFoilOnly = isFoilOnly;
	}


	public void setIsOnlineOnly(boolean isOnlineOnly) {
		this.isOnlineOnly = isOnlineOnly;
	}

	public void setIsOversized(boolean isOversized) {
		this.isOversized = isOversized;
	}


	public void setIsReserved(boolean isReserved) {
		this.isReserved = isReserved;
	}

	public void setIsTimeshifted(boolean isTimeshifted) {
		this.isTimeshifted = isTimeshifted;
	}


	public void setLayoutBean(Layout layoutBean) {
		this.layoutBean = layoutBean;
	}

	public void setLegalities(List<Legality> legalities) {
		this.legalities = legalities;
	}


	public void setLoyalty(int loyalty) {
		this.loyalty = loyalty;
	}

	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}


	public void setMultiverseId(int multiverseId) {
		this.multiverseId = multiverseId;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setNames(String names) {
		this.names = names;
	}

	public void setNumber(int number) {
		this.number = number;
	}


	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}

	public void setPower(String power) {
		this.power = power;
	}


	public void setRarityBean(Rarity rarityBean) {
		this.rarityBean = rarityBean;
	}

	public void setRulings(List<Ruling> rulings) {
		this.rulings = rulings;
	}


	public void setSetBean(Set setBean) {
		this.setBean = setBean;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public void setToughness(String toughness) {
		this.toughness = toughness;
	}

	public void setType(String type) {
		this.type = type;
	}


	public void setTypeBean(Type typeBean) {
		this.typeBean = typeBean;
	}

	public void setSubTypes(List<Type> subTypes) {
		this.subTypes = subTypes;
	}

	public void setSuperTypes(List<Type> superTypes) {
		this.superTypes = superTypes;
	}

	public void setTypes(List<Type> types) {
		this.types = types;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public void setWatermark(String watermark) {
		this.watermark = watermark;
	}

	@Override
	public String toString() {
		final int maxLen = 3;
		return "Card [id=" + id + ", multiverseId=" + multiverseId + ", name=" + name + ", sets="
				+ (sets != null ? sets.subList(0, Math.min(sets.size(), maxLen)) : null) + "]";
	}



}