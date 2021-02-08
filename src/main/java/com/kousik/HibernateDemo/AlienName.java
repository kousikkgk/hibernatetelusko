package com.kousik.HibernateDemo;

import javax.persistence.Embeddable;

@Embeddable //embed with Entity class (Alien)
public class AlienName {
	private String alienFname;
	private String alienMname;
	private String alienLname;
	
	public String getAlienFname() {
		return alienFname;
	}
	public void setAlienFname(String alienFname) {
		this.alienFname = alienFname;
	}
	public String getAlienMname() {
		return alienMname;
	}
	public void setAlienMname(String alienMname) {
		this.alienMname = alienMname;
	}
	public String getAlienLname() {
		return alienLname;
	}
	public void setAlienLname(String alienLname) {
		this.alienLname = alienLname;
	}
	
	
}
