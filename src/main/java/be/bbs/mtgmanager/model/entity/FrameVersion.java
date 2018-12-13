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
@Table(name = "frameversion", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FrameVersion.findAll", query = "SELECT f FROM FrameVersion f")
    , @NamedQuery(name = "FrameVersion.findById", query = "SELECT f FROM FrameVersion f WHERE f.id = :id")
    , @NamedQuery(name = "FrameVersion.findByFrameversionName", query = "SELECT f FROM FrameVersion f WHERE f.frameversionName = :frameversionName")})
public class FrameVersion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "frameversion_name")
    private String frameversionName;
    @OneToMany(mappedBy = "frameVersion", fetch = FetchType.EAGER)
    private Collection<Card> cardCollection;

    public FrameVersion() {
    }

    public FrameVersion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrameversionName() {
        return frameversionName;
    }

    public void setFrameversionName(String frameversionName) {
        this.frameversionName = frameversionName;
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
		return Objects.hash(frameversionName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FrameVersion))
			return false;
		FrameVersion other = (FrameVersion) obj;
		return Objects.equals(frameversionName, other.frameversionName) && id == other.id;
	}

	@Override
    public String toString() {
        return "bbsfx.entity.FrameVersion[ id=" + id + " ]";
    }
    
}
