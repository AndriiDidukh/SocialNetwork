package ua.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String text;
	private Timestamp date;

	@ManyToOne
	private UserPage senderId;

	@ManyToOne
	private UserPage reciverId;

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserPage getSenderId() {
		return senderId;
	}

	public void setSenderId(UserPage senderId) {
		this.senderId = senderId;
	}

	public UserPage getReciverId() {
		return reciverId;
	}

	public void setReciverId(UserPage reciverId) {
		this.reciverId = reciverId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
