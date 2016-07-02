package com.communication.qq;

public enum Role {
	SERVER("server"), CLIENT("client");
	private String abbreviation;

	private Role(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	@Override
	public String toString() {
		return getAbbreviation();
	}
	
	/*public static void main(String[] args) {
		Role role = Role.SERVER;
		System.out.println(role);
	}*/
}
