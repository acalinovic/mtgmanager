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
@Table(name = "type", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Type.findAll", query = "SELECT t FROM Type t")
    , @NamedQuery(name = "Type.findById", query = "SELECT t FROM Type t WHERE t.id = :id")
    , @NamedQuery(name = "Type.findByTypeName", query = "SELECT t FROM Type t WHERE t.typeName = :typeName")})
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "type_name")
    private String typeName;
    @ManyToMany(mappedBy = "types", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Card> cardsOfType;
    @ManyToMany(mappedBy = "subtypes", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Card> cardsOfSubtype;
    @Fetch(value = FetchMode.SUBSELECT)
   @ManyToMany(mappedBy = "supertypes", fetch = FetchType.EAGER)
    private Collection<Card> cardsOfSupertype;
    @Fetch(value = FetchMode.SUBSELECT)
   @OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
    private Collection<Card> cards;

    public Type() {
    }

    public Type(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @XmlTransient
    public Collection<Card> getCardsOfType() {
        return cardsOfType;
    }

    public void setCardsOfType(Collection<Card> cardCollection) {
        this.cardsOfType = cardCollection;
    }

    @XmlTransient
    public Collection<Card> getCardsOfSubtype() {
        return cardsOfSubtype;
    }

    public void setCardsOfSubtype(Collection<Card> cardCollection1) {
        this.cardsOfSubtype = cardCollection1;
    }

    @XmlTransient
    public Collection<Card> getCardsOfSupertype() {
        return cardsOfSupertype;
    }

    public void setCardsOfSupertype(Collection<Card> cardCollection2) {
        this.cardsOfSupertype = cardCollection2;
    }

    @XmlTransient
    public Collection<Card> getCards() {
        return cards;
    }

    public void setCards(Collection<Card> cardCollection3) {
        this.cards = cardCollection3;
    }

    @Override
	public int hashCode() {
		return Objects.hash(id, typeName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Type))
			return false;
		Type other = (Type) obj;
		return id == other.id && Objects.equals(typeName, other.typeName);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Type[ id=" + id + " ]";
    }
    
}
