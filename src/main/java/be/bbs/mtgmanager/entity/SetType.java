package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the SetType database table.
 * 
 */
@Entity
@Table(name="SetType")
@NamedQuery(name="SetType.findAll", query="SELECT s FROM SetType s")
public class SetType implements Serializable {
	private static final long serialVersionUID = 1L;
	private String label;
	private String name;
	private List<Set> sets;
	private int id;

	public SetType() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length=100)
	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}


	@Column(length=100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Set
	@OneToMany(mappedBy="setType", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<Set> getSets() {
		return this.sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public Set addSet(Set set) {
		getSets().add(set);
		set.setSetType(this);

		return set;
	}

	public Set removeSet(Set set) {
		getSets().remove(set);
		set.setSetType(null);

		return set;
	}

}