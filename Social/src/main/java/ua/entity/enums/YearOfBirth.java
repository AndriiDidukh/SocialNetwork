package ua.entity.enums;

public enum YearOfBirth {

	year1989(1989), year1990(1990), year1991(1991), year1992(1992), year1993(1993), year1994(1994), year1995(1995), year1996(1996), year1997(1997), year1998(1998);

	private int id;

	private YearOfBirth(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
