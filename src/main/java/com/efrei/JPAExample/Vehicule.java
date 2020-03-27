package com.efrei.JPAExample;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Vehicule {
	
	private long id;
	private String plateNumber;
	private List<Rent> rents = new ArrayList<Rent>();

	public Vehicule() {
		super();
	}

	public Vehicule(String plateNumber) {
		super();
		this.plateNumber = plateNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	@OneToMany(mappedBy="vehicule")
	@JsonIgnore
	public List<Rent> getRents() {
		return rents;
	}
	
	public void setRents(List<Rent> rents) {
		this.rents = rents;
	}

	@Override
	public String toString() {
		return "Vehicule [id=" + id + ", plateNumber=" + plateNumber + "]";
	}

}
