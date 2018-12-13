/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.bbs.mtgmanager.model.entity;

import java.io.Serializable;
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
@Table(name = "ruling", catalog = "mtgmanagerv2", schema = "mtgmanagerv2")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruling.findAll", query = "SELECT r FROM Ruling r")
    , @NamedQuery(name = "Ruling.findById", query = "SELECT r FROM Ruling r WHERE r.id = :id")
    , @NamedQuery(name = "Ruling.findByRulingDate", query = "SELECT r FROM Ruling r WHERE r.rulingDate = :rulingDate")
    , @NamedQuery(name = "Ruling.findByRulingText", query = "SELECT r FROM Ruling r WHERE r.rulingText = :rulingText")})
public class Ruling implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Column(name = "ruling_date")
    private String rulingDate;
    @Column(name = "ruling_text")
    private String rulingText;
    @JoinColumn(name = "ruling_card", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Card card;

    public Ruling() {
    }

    public Ruling(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRulingDate() {
        return rulingDate;
    }

    public void setRulingDate(String rulingDate) {
        this.rulingDate = rulingDate;
    }

    public String getRulingText() {
        return rulingText;
    }

    public void setRulingText(String rulingText) {
        this.rulingText = rulingText;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
	public int hashCode() {
		return Objects.hash(card, rulingDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ruling))
			return false;
		Ruling other = (Ruling) obj;
		return Objects.equals(card, other.card) && Objects.equals(rulingDate, other.rulingDate);
	}

	@Override
    public String toString() {
        return "bbsfx.entity.Ruling[ id=" + id + " ]";
    }
    
}
