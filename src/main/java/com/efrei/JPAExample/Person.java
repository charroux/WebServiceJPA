package com.efrei.JPAExample;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Person {
	
	private long id;
	private String name;
	private List<Rent> rents = new ArrayList<Rent>();
	
	public Person() {
		super();
	}

	public Person(String name) {
		super();
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy="person", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Rent> getRents() {
		return rents;
	}

	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", rents=" + rents + "]";
	}
	
}
