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
@Table(name = "layout", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Layout.findAll", query = "SELECT l FROM Layout l")
    , @NamedQuery(name = "Layout.findById", query = "SELECT l FROM Layout l WHERE l.id = :id")
    , @NamedQuery(name = "Layout.findByLayoutName", query = "SELECT l FROM Layout l WHERE l.layoutName = :layoutName")})
public class Layout implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "layout_name")
    private String layoutName;
    @OneToMany(mappedBy = "layout", fetch = FetchType.EAGER)
    private Collection<Card> cardCollection;

    public Layout() {
    }

    public Layout(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLayoutName() {
        return layoutName;
    }

    public void setLayoutName(String layoutName) {
        this.layoutName = layoutName;
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
		return Objects.hash(id, layoutName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Layout))
			return false;
		Layout other = (Layout) obj;
		return id == other.id && Objects.equals(layoutName, other.layoutName);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Layout[ id=" + id + " ]";
    }
    
}
