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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "color", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Color.findAll", query = "SELECT c FROM Color c")
    , @NamedQuery(name = "Color.findById", query = "SELECT c FROM Color c WHERE c.id = :id")
    , @NamedQuery(name = "Color.findByColorCode", query = "SELECT c FROM Color c WHERE c.colorCode = :colorCode")
    , @NamedQuery(name = "Color.findByColorLabel", query = "SELECT c FROM Color c WHERE c.colorLabel = :colorLabel")
    , @NamedQuery(name = "Color.findByColorRed", query = "SELECT c FROM Color c WHERE c.colorRed = :colorRed")
    , @NamedQuery(name = "Color.findByColorGreen", query = "SELECT c FROM Color c WHERE c.colorGreen = :colorGreen")
    , @NamedQuery(name = "Color.findByColorBlue", query = "SELECT c FROM Color c WHERE c.colorBlue = :colorBlue")
    , @NamedQuery(name = "Color.findByColorAlpha", query = "SELECT c FROM Color c WHERE c.colorAlpha = :colorAlpha")})
public class Color implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "color_code")
    private Character colorCode;
    @Column(name = "color_label")
    private String colorLabel;
    @Column(name = "color_red")
    private BigInteger colorRed;
    @Column(name = "color_green")
    private BigInteger colorGreen;
    @Column(name = "color_blue")
    private BigInteger colorBlue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "color_alpha")
    private Double colorAlpha;
    @ManyToMany(mappedBy = "colors", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Card> cardCollection;
    @JoinTable(name = "token_has_color", joinColumns = {
        @JoinColumn(name = "color_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "token_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
   private Collection<Token> tokenCollection;

    public Color() {
    }

    public Color(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Character getColorCode() {
        return colorCode;
    }

    public void setColorCode(Character colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorLabel() {
        return colorLabel;
    }

    public void setColorLabel(String colorLabel) {
        this.colorLabel = colorLabel;
    }

    public BigInteger getColorRed() {
        return colorRed;
    }

    public void setColorRed(BigInteger colorRed) {
        this.colorRed = colorRed;
    }

    public BigInteger getColorGreen() {
        return colorGreen;
    }

    public void setColorGreen(BigInteger colorGreen) {
        this.colorGreen = colorGreen;
    }

    public BigInteger getColorBlue() {
        return colorBlue;
    }

    public void setColorBlue(BigInteger colorBlue) {
        this.colorBlue = colorBlue;
    }

    public Double getColorAlpha() {
        return colorAlpha;
    }

    public void setColorAlpha(Double colorAlpha) {
        this.colorAlpha = colorAlpha;
    }

    @XmlTransient
    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(Collection<Card> cardCollection) {
        this.cardCollection = cardCollection;
    }

    @XmlTransient
    public Collection<Token> getTokenCollection() {
        return tokenCollection;
    }

    public void setTokenCollection(Collection<Token> tokenCollection) {
        this.tokenCollection = tokenCollection;
    }

    @Override
	public int hashCode() {
		return Objects.hash(colorCode, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Color))
			return false;
		Color other = (Color) obj;
		return Objects.equals(colorCode, other.colorCode) && id == other.id;
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Color[ id=" + id + " ]";
    }
    
}
