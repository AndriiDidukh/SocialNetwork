package ua.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GroupePost {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String text;
	private Timestamp Date;

	@ManyToOne
	private UserPage writer;

	@ManyToOne
	private Groupe owner;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public UserPage getWriter() {
		return writer;
	}

	public void setWriter(UserPage writer) {
		this.writer = writer;
	}

	public Groupe getOwner() {
		return owner;
	}

	public void setOwner(Groupe owner) {
		this.owner = owner;
	}

	public Timestamp getDate() {
		return Date;
	}

	public void setDate(Timestamp date) {
		Date = date;
	}

}
