package com.efrei.JPAExample;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Rent {
	
	private long id;
	private Person person;
	private Vehicule vehicule;
	private Date beginDate;
	private Date endDate;
	
	public Rent() {
		super();
	}

	public Rent(Date beginDate, Date endDate) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@ManyToOne
	@JsonIgnore
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Vehicule getVehicule() {
		return vehicule;
	}
	
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Rent [id=" + id + ", vehicule=" + vehicule + ", beginDate=" + beginDate + ", endDate=" + endDate + "]";
	}
	
}
