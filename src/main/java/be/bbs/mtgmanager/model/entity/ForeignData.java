/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.mtgmanager.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author boris
 */
@Entity
@Table(name = "foreigndata", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ForeignData.findAll", query = "SELECT f FROM ForeignData f")
    , @NamedQuery(name = "ForeignData.findById", query = "SELECT f FROM ForeignData f WHERE f.id = :id")
    , @NamedQuery(name = "ForeignData.findByForeigndataFlavortext", query = "SELECT f FROM ForeignData f WHERE f.foreigndataFlavortext = :foreigndataFlavortext")
    , @NamedQuery(name = "ForeignData.findByForeigndataMultiverseid", query = "SELECT f FROM ForeignData f WHERE f.foreigndataMultiverseid = :foreigndataMultiverseid")
    , @NamedQuery(name = "ForeignData.findByForeigndataName", query = "SELECT f FROM ForeignData f WHERE f.foreigndataName = :foreigndataName")
    , @NamedQuery(name = "ForeignData.findByForeigndataText", query = "SELECT f FROM ForeignData f WHERE f.foreigndataText = :foreigndataText")
    , @NamedQuery(name = "ForeignData.findByForeigndataType", query = "SELECT f FROM ForeignData f WHERE f.foreigndataType = :foreigndataType")})
public class ForeignData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "foreigndata_flavortext")
    private String foreigndataFlavortext;
    @Column(name = "foreigndata_multiverseid")
    private BigInteger foreigndataMultiverseid;
    @Column(name = "foreigndata_name")
    private String foreigndataName;
    @Column(name = "foreigndata_text")
    private String foreigndataText;
    @Column(name = "foreigndata_type")
    private String foreigndataType;
    @JoinColumn(name = "foreigndata_card", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Card card;
    @JoinColumn(name = "foreigndata_language", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Language language;

    public ForeignData() {
    }

    public ForeignData(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForeigndataFlavortext() {
        return foreigndataFlavortext;
    }

    public void setForeigndataFlavortext(String foreigndataFlavortext) {
        this.foreigndataFlavortext = foreigndataFlavortext;
    }

    public BigInteger getForeigndataMultiverseid() {
        return foreigndataMultiverseid;
    }

    public void setForeigndataMultiverseid(BigInteger foreigndataMultiverseid) {
        this.foreigndataMultiverseid = foreigndataMultiverseid;
    }

    public String getForeigndataName() {
        return foreigndataName;
    }

    public void setForeigndataName(String foreigndataName) {
        this.foreigndataName = foreigndataName;
    }

    public String getForeigndataText() {
        return foreigndataText;
    }

    public void setForeigndataText(String foreigndataText) {
        this.foreigndataText = foreigndataText;
    }

    public String getForeigndataType() {
        return foreigndataType;
    }

    public void setForeigndataType(String foreigndataType) {
        this.foreigndataType = foreigndataType;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

	@Override
	public int hashCode() {
		return Objects.hash(foreigndataMultiverseid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof ForeignData))
			return false;
		ForeignData other = (ForeignData) obj;
		return Objects.equals(foreigndataMultiverseid, other.foreigndataMultiverseid);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.ForeignData[ id=" + id + " ]";
    }
    
}
