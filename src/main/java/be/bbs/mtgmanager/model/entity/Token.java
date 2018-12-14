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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 *
 * @author boris
 */
@Entity
@Table(name = "token", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Token.findAll", query = "SELECT t FROM Token t")
    , @NamedQuery(name = "Token.findById", query = "SELECT t FROM Token t WHERE t.id = :id")
    , @NamedQuery(name = "Token.findByTokenLoyalty", query = "SELECT t FROM Token t WHERE t.tokenLoyalty = :tokenLoyalty")
    , @NamedQuery(name = "Token.findByTokenName", query = "SELECT t FROM Token t WHERE t.tokenName = :tokenName")
    , @NamedQuery(name = "Token.findByTokenNumber", query = "SELECT t FROM Token t WHERE t.tokenNumber = :tokenNumber")
    , @NamedQuery(name = "Token.findByTokenPower", query = "SELECT t FROM Token t WHERE t.tokenPower = :tokenPower")
    , @NamedQuery(name = "Token.findByTokenSide", query = "SELECT t FROM Token t WHERE t.tokenSide = :tokenSide")
    , @NamedQuery(name = "Token.findByTokenText", query = "SELECT t FROM Token t WHERE t.tokenText = :tokenText")
    , @NamedQuery(name = "Token.findByTokenToughness", query = "SELECT t FROM Token t WHERE t.tokenToughness = :tokenToughness")
    , @NamedQuery(name = "Token.findByTokenType", query = "SELECT t FROM Token t WHERE t.tokenType = :tokenType")
    , @NamedQuery(name = "Token.findByTokenUuid", query = "SELECT t FROM Token t WHERE t.tokenUuid = :tokenUuid")
    , @NamedQuery(name = "Token.findByTokenWatermark", query = "SELECT t FROM Token t WHERE t.tokenWatermark = :tokenWatermark")})
public class Token implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "token_loyalty")
    private BigInteger tokenLoyalty;
    @Column(name = "token_name")
    private String tokenName;
    @Column(name = "token_number")
    private BigInteger tokenNumber;
    @Column(name = "token_power")
    private String tokenPower;
    @Column(name = "token_side")
    private Character tokenSide;
    @Column(name = "token_text")
    private String tokenText;
    @Column(name = "token_toughness")
    private String tokenToughness;
    @Column(name = "token_type")
    private String tokenType;
    @Column(name = "token_uuid")
    private String tokenUuid;
    @Column(name = "token_watermark")
    private String tokenWatermark;
    @ManyToMany(mappedBy = "tokenCollection", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Color> colorCollection;
    @JoinColumn(name = "token_artist", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Artist artist;
    @JoinColumn(name = "token_bordercolor", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private BorderColor borderColor;
    @JoinColumn(name = "token_expansion", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Expansion expansion;
    @OneToMany(mappedBy = "token", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Card> cardCollection;

    public Token() {
    }

    public Token(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getTokenLoyalty() {
        return tokenLoyalty;
    }

    public void setTokenLoyalty(BigInteger tokenLoyalty) {
        this.tokenLoyalty = tokenLoyalty;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public BigInteger getTokenNumber() {
        return tokenNumber;
    }

    public void setTokenNumber(BigInteger tokenNumber) {
        this.tokenNumber = tokenNumber;
    }

    public String getTokenPower() {
        return tokenPower;
    }

    public void setTokenPower(String tokenPower) {
        this.tokenPower = tokenPower;
    }

    public Character getTokenSide() {
        return tokenSide;
    }

    public void setTokenSide(Character tokenSide) {
        this.tokenSide = tokenSide;
    }

    public String getTokenText() {
        return tokenText;
    }

    public void setTokenText(String tokenText) {
        this.tokenText = tokenText;
    }

    public String getTokenToughness() {
        return tokenToughness;
    }

    public void setTokenToughness(String tokenToughness) {
        this.tokenToughness = tokenToughness;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getTokenUuid() {
        return tokenUuid;
    }

    public void setTokenUuid(String tokenUuid) {
        this.tokenUuid = tokenUuid;
    }

    public String getTokenWatermark() {
        return tokenWatermark;
    }

    public void setTokenWatermark(String tokenWatermark) {
        this.tokenWatermark = tokenWatermark;
    }

    @XmlTransient
    public Collection<Color> getColorCollection() {
        return colorCollection;
    }

    public void setColorCollection(Collection<Color> colorCollection) {
        this.colorCollection = colorCollection;
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

    @XmlTransient
    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(Collection<Card> cardCollection) {
        this.cardCollection = cardCollection;
    }

    @Override
	public int hashCode() {
		return Objects.hash(id, tokenName, tokenUuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Token))
			return false;
		Token other = (Token) obj;
		return id == other.id && Objects.equals(tokenName, other.tokenName)
				&& Objects.equals(tokenUuid, other.tokenUuid);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Token[ id=" + id + " ]";
    }
    
}
