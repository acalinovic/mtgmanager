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
@Table(name = "expansiontype", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ExpansionType.findAll", query = "SELECT e FROM ExpansionType e")
    , @NamedQuery(name = "ExpansionType.findById", query = "SELECT e FROM ExpansionType e WHERE e.id = :id")
    , @NamedQuery(name = "ExpansionType.findByExpansiontypeName", query = "SELECT e FROM ExpansionType e WHERE e.expansiontypeName = :expansiontypeName")
    , @NamedQuery(name = "ExpansionType.findByExpansiontypeLabel", query = "SELECT e FROM ExpansionType e WHERE e.expansiontypeLabel = :expansiontypeLabel")})
public class ExpansionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "expansiontype_name")
    private String expansiontypeName;
    @Column(name = "expansiontype_label")
    private String expansiontypeLabel;
    @OneToMany(mappedBy = "expansionType", fetch = FetchType.LAZY)
    private Collection<Expansion> expansionCollection;

    public ExpansionType() {
    }

    public ExpansionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpansiontypeName() {
        return expansiontypeName;
    }

    public void setExpansiontypeName(String expansiontypeName) {
        this.expansiontypeName = expansiontypeName;
    }

    public String getExpansiontypeLabel() {
        return expansiontypeLabel;
    }

    public void setExpansiontypeLabel(String expansiontypeLabel) {
        this.expansiontypeLabel = expansiontypeLabel;
    }

    @XmlTransient
    public Collection<Expansion> getExpansionCollection() {
        return expansionCollection;
    }

    public void setExpansionCollection(Collection<Expansion> expansionCollection) {
        this.expansionCollection = expansionCollection;
    }

    @Override
	public int hashCode() {
		return Objects.hash(expansiontypeName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ExpansionType))
			return false;
		ExpansionType other = (ExpansionType) obj;
		return Objects.equals(expansiontypeName, other.expansiontypeName);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.ExpansionType[ id=" + id + " ]";
    }
    
}
