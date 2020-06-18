package com.javaex.phone;

public class PhoneVo {
	/* 필드 */
	private int personID;
	private String name, hp, company;

	/* 생성자 */
	public PhoneVo() {}
	
	public PhoneVo(String name, String hp, String company) {
		this.name = name;
		this.hp = hp;
		this.company = company;
	}
	
	public PhoneVo(int personID, String name, String hp, String company) {
		this.personID = personID;
		this.name = name;
		this.hp = hp;
		this.company = company;
	}

	/* getter / setter */
	// person_id
	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}

	// name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// hp
	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	// company
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	/* toString */
	@Override
	public String toString() {
		return "PhoneVo [personID=" + personID + ", name=" + name + ", hp=" + hp + ", company=" + company + "]";
	}
}
