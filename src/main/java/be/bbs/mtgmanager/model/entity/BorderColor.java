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
@Table(name = "bordercolor", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BorderColor.findAll", query = "SELECT b FROM BorderColor b")
    , @NamedQuery(name = "BorderColor.findById", query = "SELECT b FROM BorderColor b WHERE b.id = :id")
    , @NamedQuery(name = "BorderColor.findBybordercolorName", query = "SELECT b FROM BorderColor b WHERE b.bordercolorName = :bordercolorName")
    , @NamedQuery(name = "BorderColor.findBybordercolorRed", query = "SELECT b FROM BorderColor b WHERE b.bordercolorRed = :bordercolorRed")
    , @NamedQuery(name = "BorderColor.findByBordercolorGreen", query = "SELECT b FROM BorderColor b WHERE b.bordercolorGreen = :bordercolorGreen")
    , @NamedQuery(name = "BorderColor.findBybordercolorBlue", query = "SELECT b FROM BorderColor b WHERE b.bordercolorBlue = :bordercolorBlue")
    , @NamedQuery(name = "BorderColor.findBybordercolorAlpha", query = "SELECT b FROM BorderColor b WHERE b.bordercolorAlpha = :bordercolorAlpha")})
public class BorderColor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "bordercolor_name")
    private String bordercolorName;
    @Column(name = "bordercolor_red")
    private BigInteger bordercolorRed;
    @Column(name = "bordercolor_green")
    private BigInteger bordercolorGreen;
    @Column(name = "bordercolor_blue")
    private BigInteger bordercolorBlue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "bordercolor_alpha")
    private Double bordercolorAlpha;
    @OneToMany(mappedBy = "borderColor", fetch = FetchType.EAGER)
    private Collection<Token> tokenCollection;
    @OneToMany(mappedBy = "borderColor", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Card> cardCollection;

    public BorderColor() {
    }

    public BorderColor(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbordercolorName() {
        return bordercolorName;
    }

    public void setbordercolorName(String bordercolorName) {
        this.bordercolorName = bordercolorName;
    }

    public BigInteger getbordercolorRed() {
        return bordercolorRed;
    }

    public void setbordercolorRed(BigInteger bordercolorRed) {
        this.bordercolorRed = bordercolorRed;
    }

    public BigInteger getBordercolorGreen() {
        return bordercolorGreen;
    }

    public void setBordercolorGreen(BigInteger bordercolorGreen) {
        this.bordercolorGreen = bordercolorGreen;
    }

    public BigInteger getbordercolorBlue() {
        return bordercolorBlue;
    }

    public void setbordercolorBlue(BigInteger bordercolorBlue) {
        this.bordercolorBlue = bordercolorBlue;
    }

    public Double getbordercolorAlpha() {
        return bordercolorAlpha;
    }

    public void setbordercolorAlpha(Double bordercolorAlpha) {
        this.bordercolorAlpha = bordercolorAlpha;
    }

    @XmlTransient
    public Collection<Token> getTokenCollection() {
        return tokenCollection;
    }

    public void setTokenCollection(Collection<Token> tokenCollection) {
        this.tokenCollection = tokenCollection;
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
		return Objects.hash(bordercolorGreen, bordercolorAlpha, bordercolorBlue, bordercolorName, bordercolorRed,
				cardCollection, id, tokenCollection);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BorderColor))
			return false;
		BorderColor other = (BorderColor) obj;
		return Objects.equals(bordercolorGreen, other.bordercolorGreen)
				&& Objects.equals(bordercolorAlpha, other.bordercolorAlpha)
				&& Objects.equals(bordercolorBlue, other.bordercolorBlue)
				&& Objects.equals(bordercolorName, other.bordercolorName)
				&& Objects.equals(bordercolorRed, other.bordercolorRed)
				&& Objects.equals(cardCollection, other.cardCollection) && id == other.id
				&& Objects.equals(tokenCollection, other.tokenCollection);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.BorderColor[ id=" + id + " ]";
    }
    
}
