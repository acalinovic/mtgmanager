/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.mtgmanager.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author boris
 */
@Entity
@Table(name = "card", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Card.findById", query = "SELECT c FROM Card c WHERE c.id = :id")
    
    /*
	; @NamedQuery(name = "Card.findAll", query = "SELECT c FROM Card c")
    , @NamedQuery(name = "Card.findByCardConvertedmanacost", query = "SELECT c FROM Card c WHERE c.cardConvertedmanacost = :cardConvertedmanacost")
    , @NamedQuery(name = "Card.findByCardFaceconvertedmanacost", query = "SELECT c FROM Card c WHERE c.cardFaceconvertedmanacost = :cardFaceconvertedmanacost")
    , @NamedQuery(name = "Card.findByCardFlavortext", query = "SELECT c FROM Card c WHERE c.cardFlavortext = :cardFlavortext")
    , @NamedQuery(name = "Card.findByCardHasfoil", query = "SELECT c FROM Card c WHERE c.cardHasfoil = :cardHasfoil")
    , @NamedQuery(name = "Card.findByCardHasnonfoil", query = "SELECT c FROM Card c WHERE c.cardHasnonfoil = :cardHasnonfoil")
    , @NamedQuery(name = "Card.findByCardIsfoilonly", query = "SELECT c FROM Card c WHERE c.cardIsfoilonly = :cardIsfoilonly")
    , @NamedQuery(name = "Card.findByCardIsonlineonly", query = "SELECT c FROM Card c WHERE c.cardIsonlineonly = :cardIsonlineonly")
    , @NamedQuery(name = "Card.findByCardIsoversized", query = "SELECT c FROM Card c WHERE c.cardIsoversized = :cardIsoversized")
    , @NamedQuery(name = "Card.findByCardIsreserved", query = "SELECT c FROM Card c WHERE c.cardIsreserved = :cardIsreserved")
    , @NamedQuery(name = "Card.findByCardIstimeshifted", query = "SELECT c FROM Card c WHERE c.cardIstimeshifted = :cardIstimeshifted")
    , @NamedQuery(name = "Card.findByCardLoyalty", query = "SELECT c FROM Card c WHERE c.cardLoyalty = :cardLoyalty")
    , @NamedQuery(name = "Card.findByCardManacost", query = "SELECT c FROM Card c WHERE c.cardManacost = :cardManacost")
    , @NamedQuery(name = "Card.findByCardMultiverseid", query = "SELECT c FROM Card c WHERE c.cardMultiverseid = :cardMultiverseid")
    , @NamedQuery(name = "Card.findByCardName", query = "SELECT c FROM Card c WHERE c.cardName = :cardName")
    , @NamedQuery(name = "Card.findByCardNames", query = "SELECT c FROM Card c WHERE c.cardNames = :cardNames")
    , @NamedQuery(name = "Card.findByCardNumber", query = "SELECT c FROM Card c WHERE c.cardNumber = :cardNumber")
    , @NamedQuery(name = "Card.findByCardOriginaltext", query = "SELECT c FROM Card c WHERE c.cardOriginaltext = :cardOriginaltext")
    , @NamedQuery(name = "Card.findByCardPower", query = "SELECT c FROM Card c WHERE c.cardPower = :cardPower")
    , @NamedQuery(name = "Card.findByCardText", query = "SELECT c FROM Card c WHERE c.cardText = :cardText")
    , @NamedQuery(name = "Card.findByCardToughness", query = "SELECT c FROM Card c WHERE c.cardToughness = :cardToughness")
    , @NamedQuery(name = "Card.findByCardType", query = "SELECT c FROM Card c WHERE c.cardType = :cardType")
    , @NamedQuery(name = "Card.findByCardUuid", query = "SELECT c FROM Card c WHERE c.cardUuid = :cardUuid")
    , @NamedQuery(name = "Card.findByCardWatermark", query = "SELECT c FROM Card c WHERE c.cardWatermark = :cardWatermark")
    */
})
public class Card implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "card_id_seq" )
    //@Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "card_convertedmanacost")
    private Double cardConvertedmanacost;
    @Column(name = "card_faceconvertedmanacost")
    private Double cardFaceconvertedmanacost;
    @Column(name = "card_flavortext")
    private String cardFlavortext;
    @Column(name = "card_hasfoil")
    private boolean cardHasfoil;
    @Column(name = "card_hasnonfoil")
    private boolean cardHasnonfoil;
    @Column(name = "card_isfoilonly")
    private boolean cardIsfoilonly;
    @Column(name = "card_isonlineonly")
    private boolean cardIsonlineonly;
    @Column(name = "card_isoversized")
    private boolean cardIsoversized;
    @Column(name = "card_isreserved")
    private boolean cardIsreserved;
    @Column(name = "card_istimeshifted")
    private boolean cardIstimeshifted;
    @Column(name = "card_loyalty")
    private BigInteger cardLoyalty;
    @Column(name = "card_manacost")
    private String cardManacost;
    @Column(name = "card_multiverseid")
    private BigInteger cardMultiverseid;
    @Column(name = "card_name")
    private String cardName;
    @Column(name = "card_names")
    private String cardNames;
    @Column(name = "card_number")
    private BigInteger cardNumber;
    @Column(name = "card_originaltext")
    private String cardOriginaltext;
    @Column(name = "card_power")
    private String cardPower;
    @Column(name = "card_text")
    private String cardText;
    @Column(name = "card_toughness")
    private String cardToughness;
    @Column(name = "card_type")
    private String cardType;
    @Column(name = "card_uuid")
    private String cardUuid;
    @Column(name = "card_watermark")
    private String cardWatermark;
    
    
    @JoinTable(name = "card_has_color", joinColumns = {
        @JoinColumn(name = "card_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "color_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Color> colors;
    
    
    @JoinTable(name = "card_has_type", joinColumns = {
        @JoinColumn(name = "card_id_for_type", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "type_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Type> types;
    
    
    @JoinTable(name = "card_has_subtype", joinColumns = {
        @JoinColumn(name = "card_id_for_subtype", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "type_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Type> subtypes;
    
    
    @JoinTable(name = "card_has_supertype", joinColumns = {
    		@JoinColumn(name = "card_id_for_supertype", referencedColumnName = "id")}, inverseJoinColumns = {
    				@JoinColumn(name = "type_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Type> supertypes;
    
    
    @JoinTable(name = "card_has_legality", joinColumns = {
        @JoinColumn(name = "card_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "legality_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
   private Collection<Legality> legalityCollection;
    
    
    @JoinTable(name = "card_has_printing", joinColumns = {
        @JoinColumn(name = "card_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "set_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
   private Collection<Expansion> expansionCollection;
    
    
    @JoinTable(name = "card_has_variation", joinColumns = {
        @JoinColumn(name = "card_uuid")}, 
    		inverseJoinColumns = {
    				@JoinColumn(name = "variation_uuid")
    				})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Card> variations;
        
    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<ForeignData> foreignDataCollection;
    
    
    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Ruling> rulingCollection;
    
    
    @JoinColumn(name = "card_artist", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Artist artist;
    
    
    @JoinColumn(name = "card_bordercolor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BorderColor borderColor;
    
    
    @JoinColumn(name = "card_expansion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Expansion expansion;
    
    
    @JoinColumn(name = "card_frameversion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FrameVersion frameVersion;
    
    
    @JoinColumn(name = "card_layout", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Layout layout;
    
    
    @JoinColumn(name = "card_rarity", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Rarity rarity;
    
    
    @JoinColumn(name = "card_tokenreference", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Token token;
    
    
    @JoinColumn(name = "card_originaltype", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Type type;

    public Card() {
    }

    public Card(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getCardConvertedmanacost() {
        return cardConvertedmanacost;
    }

    public void setCardConvertedmanacost(Double cardConvertedmanacost) {
        this.cardConvertedmanacost = cardConvertedmanacost;
    }

    public Double getCardFaceconvertedmanacost() {
        return cardFaceconvertedmanacost;
    }

    public void setCardFaceconvertedmanacost(Double cardFaceconvertedmanacost) {
        this.cardFaceconvertedmanacost = cardFaceconvertedmanacost;
    }

    public String getCardFlavortext() {
        return cardFlavortext;
    }

    public void setCardFlavortext(String cardFlavortext) {
        this.cardFlavortext = cardFlavortext;
    }

    public boolean getCardHasfoil() {
        return cardHasfoil;
    }

    public void setCardHasfoil(boolean cardHasfoil) {
        this.cardHasfoil = cardHasfoil;
    }

    public boolean getCardHasnonfoil() {
        return cardHasnonfoil;
    }

    public void setCardHasnonfoil(boolean cardHasnonfoil) {
        this.cardHasnonfoil = cardHasnonfoil;
    }

    public boolean getCardIsfoilonly() {
        return cardIsfoilonly;
    }

    public void setCardIsfoilonly(boolean cardIsfoilonly) {
        this.cardIsfoilonly = cardIsfoilonly;
    }

    public boolean getCardIsonlineonly() {
        return cardIsonlineonly;
    }

    public void setCardIsonlineonly(boolean cardIsonlineonly) {
        this.cardIsonlineonly = cardIsonlineonly;
    }

    public boolean getCardIsoversized() {
        return cardIsoversized;
    }

    public void setCardIsoversized(boolean cardIsoversized) {
        this.cardIsoversized = cardIsoversized;
    }

    public boolean getCardIsreserved() {
        return cardIsreserved;
    }

    public void setCardIsreserved(boolean cardIsreserved) {
        this.cardIsreserved = cardIsreserved;
    }

    public boolean getCardIstimeshifted() {
        return cardIstimeshifted;
    }

    public void setCardIstimeshifted(boolean cardIstimeshifted) {
        this.cardIstimeshifted = cardIstimeshifted;
    }

    public BigInteger getCardLoyalty() {
        return cardLoyalty;
    }

    public void setCardLoyalty(BigInteger cardLoyalty) {
        this.cardLoyalty = cardLoyalty;
    }

    public String getCardManacost() {
        return cardManacost;
    }

    public void setCardManacost(String cardManacost) {
        this.cardManacost = cardManacost;
    }

    public BigInteger getCardMultiverseid() {
        return cardMultiverseid;
    }

    public void setCardMultiverseid(BigInteger cardMultiverseid) {
        this.cardMultiverseid = cardMultiverseid;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNames() {
        return cardNames;
    }

    public void setCardNames(String cardNames) {
        this.cardNames = cardNames;
    }

    public BigInteger getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(BigInteger cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardOriginaltext() {
        return cardOriginaltext;
    }

    public void setCardOriginaltext(String cardOriginaltext) {
        this.cardOriginaltext = cardOriginaltext;
    }

    public String getCardPower() {
        return cardPower;
    }

    public void setCardPower(String cardPower) {
        this.cardPower = cardPower;
    }

    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public String getCardToughness() {
        return cardToughness;
    }

    public void setCardToughness(String cardToughness) {
        this.cardToughness = cardToughness;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardUuid() {
        return cardUuid;
    }

    public void setCardUuid(String cardUuid) {
        this.cardUuid = cardUuid;
    }

    public String getCardWatermark() {
        return cardWatermark;
    }

    public void setCardWatermark(String cardWatermark) {
        this.cardWatermark = cardWatermark;
    }

    @XmlTransient
    public Collection<Color> getColors() {
        return colors;
    }

    public void setColors(Collection<Color> colorCollection) {
        this.colors = colorCollection;
    }

    @XmlTransient
    public Collection<Type> getTypes() {
        return types;
    }

    public void setTypes(Collection<Type> typeCollection) {
        this.types = typeCollection;
    }

    @XmlTransient
    public Collection<Type> getSubtypes() {
        return subtypes;
    }

    public void setSubtypes(Collection<Type> typeCollection1) {
        this.subtypes = typeCollection1;
    }

    @XmlTransient
    public Collection<Legality> getLegalityCollection() {
        return legalityCollection;
    }

    public void setLegalityCollection(Collection<Legality> legalityCollection) {
        this.legalityCollection = legalityCollection;
    }

    @XmlTransient
    public Collection<Expansion> getExpansionCollection() {
        return expansionCollection;
    }

    public void setExpansionCollection(Collection<Expansion> expansionCollection) {
        this.expansionCollection = expansionCollection;
    }

    @XmlTransient
    public Collection<Card> getVariations() {
        return variations;
    }

    public void setVariations(Collection<Card> cardCollection) {
        this.variations = cardCollection;
    }

    /*
    @XmlTransient
    public Collection<Card> getCardCollection1() {
        return cardCollection1;
    }

    public void setCardCollection1(Collection<Card> cardCollection1) {
        this.cardCollection1 = cardCollection1;
    }
*/
    @XmlTransient
    public Collection<Type> getSupertypes() {
        return supertypes;
    }

    public void setSupertypes(Collection<Type> typeCollection2) {
        this.supertypes = typeCollection2;
    }

    @XmlTransient
    public Collection<ForeignData> getForeignDataCollection() {
        return foreignDataCollection;
    }

    public void setForeignDataCollection(Collection<ForeignData> foreignDataCollection) {
        this.foreignDataCollection = foreignDataCollection;
    }

    @XmlTransient
    public Collection<Ruling> getRulingCollection() {
        return rulingCollection;
    }

    public void setRulingCollection(Collection<Ruling> rulingCollection) {
        this.rulingCollection = rulingCollection;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public BorderColor getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(BorderColor borderColor) {
        this.borderColor = borderColor;
    }

    public Expansion getExpansion() {
        return expansion;
    }

    public void setExpansion(Expansion expansion) {
        this.expansion = expansion;
    }

    public FrameVersion getFrameVersion() {
        return frameVersion;
    }

    public void setFrameVersion(FrameVersion frameVersion) {
        this.frameVersion = frameVersion;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

	@Override
	public int hashCode() {
		return Objects.hash(cardUuid);
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
		return Objects.equals(cardUuid, other.cardUuid);
	}

	@Override
	public String toString() {
		return String.format("Card [cardName=%s, cardType=%s, expansion=%s]", cardName, cardType, expansion);
	}
    
}
