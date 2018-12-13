/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.mtgmanager.model.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author boris
 */
@Entity
@Table(name = "legality", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Legality.findAll", query = "SELECT l FROM Legality l")
    , @NamedQuery(name = "Legality.findById", query = "SELECT l FROM Legality l WHERE l.id = :id")
    , @NamedQuery(name = "Legality.findByLegalityName", query = "SELECT l FROM Legality l WHERE l.legalityName = :legalityName")})
public class Legality implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "legality_name")
    private String legalityName;
    @ManyToMany(mappedBy = "legalityCollection", fetch = FetchType.EAGER)
    private Collection<Card> cardCollection;

    public Legality() {
    }

    public Legality(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLegalityName() {
        return legalityName;
    }

    public void setLegalityName(String legalityName) {
        this.legalityName = legalityName;
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
		return Objects.hash(id, legalityName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Legality))
			return false;
		Legality other = (Legality) obj;
		return id == other.id && Objects.equals(legalityName, other.legalityName);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Legality[ id=" + id + " ]";
    }
    
}
