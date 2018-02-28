package ua.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Friend {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String s;

	@ManyToOne
	private UserPage friend;

	@ManyToOne
	private UserPage friend2;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserPage getFriend() {
		return friend;
	}

	public void setFriend(UserPage friend) {
		this.friend = friend;
	}

	public UserPage getFriend2() {
		return friend2;
	}

	public void setFriend2(UserPage friend2) {
		this.friend2 = friend2;
	}

}
