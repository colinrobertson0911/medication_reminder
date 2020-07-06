package com.github.robbo1811.medication_reminder.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medication_gen")
	@SequenceGenerator(name = "medication_gen", sequenceName = "MEDICATION_SEQ", allocationSize = 1)
	private long medicationId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String dosage;

	@Column
	private String condition;

	@Column(nullable = false)
	private int timesAWeek;

	@Column(nullable = false)
	private int timesADay;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "timeToTake")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private Date timeToTake;

	@Column(nullable = false)
	private int quantity;

	@Column(nullable = false)
	private int pillsLeft;

	@Column
	private boolean refill;


	public Medication() {
	}

	public Medication(String name, String dosage) {
		this.name = name;
		this.dosage = dosage;
	}

	public Medication(String name, String dosage, String condition, int timesAWeek, int timesADay, Date timeToTake, int quantity, int pillsLeft, boolean refill) {
		this.name = name;
		this.dosage = dosage;
		this.condition = condition;
		this.timesAWeek = timesAWeek;
		this.timesADay = timesADay;
		this.timeToTake = timeToTake;
		this.quantity = quantity;
		this.pillsLeft = pillsLeft;
		this.refill = refill;
	}

	public long getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(long medicationId) {
		this.medicationId = medicationId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getTimesAWeek() {
		return timesAWeek;
	}

	public void setTimesAWeek(int timesAWeek) {
		this.timesAWeek = timesAWeek;
	}

	public int getTimesADay() {
		return timesADay;
	}

	public void setTimesADay(int timesADay) {
		this.timesADay = timesADay;
	}

	public Date getTimeToTake() {
		return timeToTake;
	}

	public void setTimeToTake(Date timeToTake) {
		this.timeToTake = timeToTake;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPillsLeft() {
		return pillsLeft;
	}

	public void setPillsLeft(int pillsLeft) {
		this.pillsLeft = pillsLeft;
	}

	public boolean isRefill() {
		return refill;
	}

	public void setRefill(boolean refill) {
		this.refill = refill;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Medication)) return false;
		Medication that = (Medication) o;
		return getMedicationId() == that.getMedicationId() &&
				getTimesAWeek() == that.getTimesAWeek() &&
				getTimesADay() == that.getTimesADay() &&
				getQuantity() == that.getQuantity() &&
				getPillsLeft() == that.getPillsLeft() &&
				isRefill() == that.isRefill() &&
				getName().equals(that.getName()) &&
				getDosage().equals(that.getDosage()) &&
				Objects.equals(getCondition(), that.getCondition()) &&
				Objects.equals(getTimeToTake(), that.getTimeToTake());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getMedicationId(), getName(), getDosage(), getCondition(), getTimesAWeek(), getTimesADay(), getTimeToTake(), getQuantity(), getPillsLeft(), isRefill());
	}

	@Override
	public String toString() {
		return "Medication{" +
				"medicationId=" + medicationId +
				", name='" + name + '\'' +
				", dosage='" + dosage + '\'' +
				", condition='" + condition + '\'' +
				", timesAWeek=" + timesAWeek +
				", timesADay=" + timesADay +
				", timeToTake=" + timeToTake +
				", quantity=" + quantity +
				", pillsLeft=" + pillsLeft +
				", refill=" + refill +
				'}';
	}
}
