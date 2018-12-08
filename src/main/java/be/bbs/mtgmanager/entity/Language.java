package be.bbs.mtgmanager.entity;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;


/**
 * The persistent class for the Language database table.
 * 
 */
@Entity
@Table(name="Language")
@NamedQuery(name="Language.findAll", query="SELECT l FROM Language l")
public class Language implements Serializable {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private List<ForeignData> foreignDatas;
	private int id;

	public Language() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(length=2)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	@Column(length=100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to ForeignData
	@OneToMany(mappedBy="languageBean", fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	public List<ForeignData> getForeignDatas() {
		return this.foreignDatas;
	}

	public void setForeignDatas(List<ForeignData> foreignDatas) {
		this.foreignDatas = foreignDatas;
	}

	public ForeignData addForeignData(ForeignData foreignData) {
		getForeignDatas().add(foreignData);
		foreignData.setLanguageBean(this);

		return foreignData;
	}

	public ForeignData removeForeignData(ForeignData foreignData) {
		getForeignDatas().remove(foreignData);
		foreignData.setLanguageBean(null);

		return foreignData;
	}

}