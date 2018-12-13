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

/**
 *
 * @author boris
 */
@Entity
@Table(name = "rarity", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rarity.findAll", query = "SELECT r FROM Rarity r")
    , @NamedQuery(name = "Rarity.findById", query = "SELECT r FROM Rarity r WHERE r.id = :id")
    , @NamedQuery(name = "Rarity.findByRarityName", query = "SELECT r FROM Rarity r WHERE r.rarityName = :rarityName")
    , @NamedQuery(name = "Rarity.findByRarityRed", query = "SELECT r FROM Rarity r WHERE r.rarityRed = :rarityRed")
    , @NamedQuery(name = "Rarity.findByRarityGreen", query = "SELECT r FROM Rarity r WHERE r.rarityGreen = :rarityGreen")
    , @NamedQuery(name = "Rarity.findByRarityBlue", query = "SELECT r FROM Rarity r WHERE r.rarityBlue = :rarityBlue")
    , @NamedQuery(name = "Rarity.findByRarityAlpha", query = "SELECT r FROM Rarity r WHERE r.rarityAlpha = :rarityAlpha")})
public class Rarity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "rarity_name")
    private String rarityName;
    @Column(name = "rarity_red")
    private BigInteger rarityRed;
    @Column(name = "rarity_green")
    private BigInteger rarityGreen;
    @Column(name = "rarity_blue")
    private BigInteger rarityBlue;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rarity_alpha")
    private Double rarityAlpha;
    @OneToMany(mappedBy = "rarity", fetch = FetchType.EAGER)
    private Collection<Card> cardCollection;

    public Rarity() {
    }

    public Rarity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRarityName() {
        return rarityName;
    }

    public void setRarityName(String rarityName) {
        this.rarityName = rarityName;
    }

    public BigInteger getRarityRed() {
        return rarityRed;
    }

    public void setRarityRed(BigInteger rarityRed) {
        this.rarityRed = rarityRed;
    }

    public BigInteger getRarityGreen() {
        return rarityGreen;
    }

    public void setRarityGreen(BigInteger rarityGreen) {
        this.rarityGreen = rarityGreen;
    }

    public BigInteger getRarityBlue() {
        return rarityBlue;
    }

    public void setRarityBlue(BigInteger rarityBlue) {
        this.rarityBlue = rarityBlue;
    }

    public Double getRarityAlpha() {
        return rarityAlpha;
    }

    public void setRarityAlpha(Double rarityAlpha) {
        this.rarityAlpha = rarityAlpha;
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
		return Objects.hash(id, rarityName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Rarity))
			return false;
		Rarity other = (Rarity) obj;
		return id == other.id && Objects.equals(rarityName, other.rarityName);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Rarity[ id=" + id + " ]";
    }
    
}
