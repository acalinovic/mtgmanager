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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "expansion", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Expansion.findAll", query = "SELECT e FROM Expansion e")
    , @NamedQuery(name = "Expansion.findById", query = "SELECT e FROM Expansion e WHERE e.id = :id")
    , @NamedQuery(name = "Expansion.findByExpansionBasesetsize", query = "SELECT e FROM Expansion e WHERE e.expansionBasesetsize = :expansionBasesetsize")
    , @NamedQuery(name = "Expansion.findByExpansionBlock", query = "SELECT e FROM Expansion e WHERE e.expansionBlock = :expansionBlock")
    , @NamedQuery(name = "Expansion.findByExpansionBoosterv3", query = "SELECT e FROM Expansion e WHERE e.expansionBoosterv3 = :expansionBoosterv3")
    , @NamedQuery(name = "Expansion.findByExpansionCode", query = "SELECT e FROM Expansion e WHERE e.expansionCode = :expansionCode")
    , @NamedQuery(name = "Expansion.findByExpansionIsonlineonly", query = "SELECT e FROM Expansion e WHERE e.expansionIsonlineonly = :expansionIsonlineonly")
    , @NamedQuery(name = "Expansion.findByExpansionMeta", query = "SELECT e FROM Expansion e WHERE e.expansionMeta = :expansionMeta")
    , @NamedQuery(name = "Expansion.findByExpansionMtgocode", query = "SELECT e FROM Expansion e WHERE e.expansionMtgocode = :expansionMtgocode")
    , @NamedQuery(name = "Expansion.findByExpansionName", query = "SELECT e FROM Expansion e WHERE e.expansionName = :expansionName")
    , @NamedQuery(name = "Expansion.findByExpansionReleasedate", query = "SELECT e FROM Expansion e WHERE e.expansionReleasedate = :expansionReleasedate")
    , @NamedQuery(name = "Expansion.findByExpansionTotalsetsize", query = "SELECT e FROM Expansion e WHERE e.expansionTotalsetsize = :expansionTotalsetsize")})
public class Expansion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "expansion_basesetsize")
    private BigInteger expansionBasesetsize;
    @Column(name = "expansion_block")
    private String expansionBlock;
    @Column(name = "expansion_boosterv3")
    private String expansionBoosterv3;
    @Column(name = "expansion_code")
    private String expansionCode;
    @Column(name = "expansion_isonlineonly")
    private boolean expansionIsonlineonly;
    @Column(name = "expansion_meta")
    private String expansionMeta;
    @Column(name = "expansion_mtgocode")
    private String expansionMtgocode;
    @Column(name = "expansion_name")
    private String expansionName;
    @Column(name = "expansion_releasedate")
    private String expansionReleasedate;
    @Column(name = "expansion_totalsetsize")
    private BigInteger expansionTotalsetsize;
    @ManyToMany(mappedBy = "expansionCollection", fetch = FetchType.LAZY)
    private Collection<Card> cardCollection;
    @JoinColumn(name = "expansion_expansiontype", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ExpansionType expansionType;
    @OneToMany(mappedBy = "expansion", fetch = FetchType.LAZY)
    private Collection<Token> tokenCollection;
    @Fetch(value = FetchMode.SUBSELECT)
    @OneToMany(mappedBy = "expansion", fetch = FetchType.LAZY)
    private Collection<Card> cardCollection1;

    public Expansion() {
    }

    public Expansion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigInteger getExpansionBasesetsize() {
        return expansionBasesetsize;
    }

    public void setExpansionBasesetsize(BigInteger expansionBasesetsize) {
        this.expansionBasesetsize = expansionBasesetsize;
    }

    public String getExpansionBlock() {
        return expansionBlock;
    }

    public void setExpansionBlock(String expansionBlock) {
        this.expansionBlock = expansionBlock;
    }

    public String getExpansionBoosterv3() {
        return expansionBoosterv3;
    }

    public void setExpansionBoosterv3(String expansionBoosterv3) {
        this.expansionBoosterv3 = expansionBoosterv3;
    }

    public String getExpansionCode() {
        return expansionCode;
    }

    public void setExpansionCode(String expansionCode) {
        this.expansionCode = expansionCode;
    }

    public boolean getExpansionIsonlineonly() {
        return expansionIsonlineonly;
    }

    public void setExpansionIsonlineonly(boolean expansionIsonlineonly) {
        this.expansionIsonlineonly = expansionIsonlineonly;
    }

    public String getExpansionMeta() {
        return expansionMeta;
    }

    public void setExpansionMeta(String expansionMeta) {
        this.expansionMeta = expansionMeta;
    }

    public String getExpansionMtgocode() {
        return expansionMtgocode;
    }

    public void setExpansionMtgocode(String expansionMtgocode) {
        this.expansionMtgocode = expansionMtgocode;
    }

    public String getExpansionName() {
        return expansionName;
    }

    public void setExpansionName(String expansionName) {
        this.expansionName = expansionName;
    }

    public String getExpansionReleasedate() {
        return expansionReleasedate;
    }

    public void setExpansionReleasedate(String expansionReleasedate) {
        this.expansionReleasedate = expansionReleasedate;
    }

    public BigInteger getExpansionTotalsetsize() {
        return expansionTotalsetsize;
    }

    public void setExpansionTotalsetsize(BigInteger expansionTotalsetsize) {
        this.expansionTotalsetsize = expansionTotalsetsize;
    }

    @XmlTransient
    public Collection<Card> getCardCollection() {
        return cardCollection;
    }

    public void setCardCollection(Collection<Card> cardCollection) {
        this.cardCollection = cardCollection;
    }

    public ExpansionType getExpansionType() {
        return expansionType;
    }

    public void setExpansionType(ExpansionType expansionType) {
        this.expansionType = expansionType;
    }

    @XmlTransient
    public Collection<Token> getTokenCollection() {
        return tokenCollection;
    }

    public void setTokenCollection(Collection<Token> tokenCollection) {
        this.tokenCollection = tokenCollection;
    }

    @XmlTransient
    public Collection<Card> getCardCollection1() {
        return cardCollection1;
    }

    public void setCardCollection1(Collection<Card> cardCollection1) {
        this.cardCollection1 = cardCollection1;
    }

    @Override
	public int hashCode() {
		return Objects.hash(expansionCode, expansionName, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Expansion))
			return false;
		Expansion other = (Expansion) obj;
		return Objects.equals(expansionCode, other.expansionCode) && Objects.equals(expansionName, other.expansionName)
				&& id == other.id;
	}

	@Override
	public String toString() {
		return "Expansion [id=" + id + ", expansionBasesetsize=" + expansionBasesetsize + ", expansionBlock="
				+ expansionBlock + ", expansionBoosterv3=" + expansionBoosterv3 + ", expansionCode=" + expansionCode
				+ ", expansionIsonlineonly=" + expansionIsonlineonly + ", expansionMeta=" + expansionMeta
				+ ", expansionMtgocode=" + expansionMtgocode + ", expansionName=" + expansionName
				+ ", expansionReleasedate=" + expansionReleasedate + ", expansionTotalsetsize=" + expansionTotalsetsize
				+ ", expansionType=" + expansionType + "]";
	}
	
	
    
}
