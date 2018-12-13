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
@Table(name = "language", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")
    , @NamedQuery(name = "Language.findById", query = "SELECT l FROM Language l WHERE l.id = :id")
    , @NamedQuery(name = "Language.findByLanguageName", query = "SELECT l FROM Language l WHERE l.languageName = :languageName")
    , @NamedQuery(name = "Language.findByLanguageCode", query = "SELECT l FROM Language l WHERE l.languageCode = :languageCode")})
public class Language implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "language_name")
    private String languageName;
    @Column(name = "language_code")
    private String languageCode;
    @OneToMany(mappedBy = "language", fetch = FetchType.EAGER)
    private Collection<ForeignData> foreignDataCollection;

    public Language() {
    }

    public Language(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    @XmlTransient
    public Collection<ForeignData> getForeignDataCollection() {
        return foreignDataCollection;
    }

    public void setForeignDataCollection(Collection<ForeignData> foreignDataCollection) {
        this.foreignDataCollection = foreignDataCollection;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, languageName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Language))
			return false;
		Language other = (Language) obj;
		return id == other.id && Objects.equals(languageName, other.languageName);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Language[ id=" + id + " ]";
    }
    
}
