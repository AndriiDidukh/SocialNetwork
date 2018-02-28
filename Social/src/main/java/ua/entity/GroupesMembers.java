package ua.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GroupesMembers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private UserPage member;

	@ManyToOne
	private Groupe groupe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserPage getMember() {
		return member;
	}

	public void setMember(UserPage member) {
		this.member = member;
	}

	public Groupe getGroupe() {
		return groupe;
	}

	public void setGroupe(Groupe groupe) {
		this.groupe = groupe;
	}

}
