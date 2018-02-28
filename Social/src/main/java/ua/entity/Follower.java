package ua.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Follower {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private UserPage followTo;

	@ManyToOne
	private UserPage follower;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserPage getFollowTo() {
		return followTo;
	}

	public void setFollowTo(UserPage followTo) {
		this.followTo = followTo;
	}

	public UserPage getFollower() {
		return follower;
	}

	public void setFollower(UserPage follower) {
		this.follower = follower;
	}
}
