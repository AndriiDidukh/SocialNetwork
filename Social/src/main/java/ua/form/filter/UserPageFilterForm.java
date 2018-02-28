package ua.form.filter;

import java.util.ArrayList;
import java.util.List;

import ua.entity.enums.Country;
import ua.entity.enums.Gender;
import ua.entity.enums.MounthOfBirth;

public class UserPageFilterForm {

	private String search;

	private String name = "";

	private String surname = "";

	private String email = "";

	private String password = "";

	private String phone = "";

	private String City = "";

	private List<Country> countryIds = new ArrayList<>();

	private List<Integer> dayOfBirthIds = new ArrayList<>();

	private List<MounthOfBirth> mounthOfBirthIds = new ArrayList<>();

	private List<Integer> yearOfBirthIds = new ArrayList<>();

	private List<Gender> sexIds = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public List<Integer> getDayOfBirthIds() {
		return dayOfBirthIds;
	}

	public void setDayOfBirthIds(List<Integer> dayOfBirthIds) {
		this.dayOfBirthIds = dayOfBirthIds;
	}

	public List<Country> getCountryIds() {
		return countryIds;
	}

	public void setCountryIds(List<Country> countryIds) {
		this.countryIds = countryIds;
	}

	public List<MounthOfBirth> getMounthOfBirthIds() {
		return mounthOfBirthIds;
	}

	public void setMounthOfBirthIds(List<MounthOfBirth> mounthOfBirthIds) {
		this.mounthOfBirthIds = mounthOfBirthIds;
	}

	public List<Integer> getYearOfBirthIds() {
		return yearOfBirthIds;
	}

	public void setYearOfBirthIds(List<Integer> yearOfBirthIds) {
		this.yearOfBirthIds = yearOfBirthIds;
	}

	public List<Gender> getSexIds() {
		return sexIds;
	}

	public void setSexIds(List<Gender> sexIds) {
		this.sexIds = sexIds;
	}

}