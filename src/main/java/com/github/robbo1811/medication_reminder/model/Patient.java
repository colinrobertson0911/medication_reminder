package com.github.robbo1811.medication_reminder.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "patient")
public class Patient extends User {


	@Column
	private String weight;

	@Column
	private String height;

	@Column
	private int age;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "patient_medication", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "medicationId"))
	private List<Medication> medication;



	public Patient() {
	}

	public Patient(String username, String password, String firstname, String lastname, String email, Role role) {
		super(username, password, firstname, lastname, email, role);
		this.medication = new ArrayList<>();
	}

	public Patient(String weight, String height, int age, List<Medication> medication) {
		this.weight = weight;
		this.height = height;
		this.age = age;
		this.medication = medication;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Medication> getMedication() {
		return medication;
	}

	public void setMedication(List<Medication> medication) {
		this.medication = medication;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Patient)) return false;
		if (!super.equals(o)) return false;
		Patient patient = (Patient) o;
		return getAge() == patient.getAge() &&
				Objects.equals(getWeight(), patient.getWeight()) &&
				Objects.equals(getHeight(), patient.getHeight()) &&
				Objects.equals(getMedication(), patient.getMedication());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getWeight(), getHeight(), getAge(), getMedication());
	}

	@Override
	public String toString() {
		return "Patient{" +
				"weight='" + weight + '\'' +
				", height='" + height + '\'' +
				", age=" + age +
				", medication=" + medication +
				'}';
	}
}
